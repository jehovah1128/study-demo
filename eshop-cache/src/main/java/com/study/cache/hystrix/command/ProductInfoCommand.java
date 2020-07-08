package com.study.cache.hystrix.command;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.study.common.util.HttpClientUtil;
import com.study.entity.Product;
import com.study.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductInfoCommand extends HystrixCommand<ProductVo> {

    private Long productId;

    public static final  HystrixCommandKey COMMAND_KEY=HystrixCommandKey.Factory.asKey("ProductInfoCommand");

    public ProductInfoCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoGroup"))
                .andCommandKey(COMMAND_KEY)
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withRequestCacheEnabled(false)
                        .withExecutionTimeoutInMilliseconds(200)
                )
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("getProductInfoPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(15)
                        .withMaxQueueSize(20)
                        .withQueueSizeRejectionThreshold(10)
                )
        );
        this.productId = productId;
    }

    @Override
    protected ProductVo run() throws Exception {
        log.info("do run");
//        Thread.sleep(2000);
        String url = "http://127.0.0.1:9999/product/info?productId=" + productId;
        String result = HttpClientUtil.doGet(url);
        ProductVo product = JSON.parseObject(result, ProductVo.class);
        return product;
    }

    @Override
    protected ProductVo getFallback() {
        ProductVo vo = new ProductVo();
        vo.setName("限流商品");
        return vo;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(productId);
    }

    public static void flushCache(Long productId){
        HystrixRequestCache.getInstance(COMMAND_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(productId));
    }
}
