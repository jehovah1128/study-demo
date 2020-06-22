package com.study.cache.hystrix.command;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.study.common.util.HttpClientUtil;
import com.study.entity.Product;

public class ProductInfoCommand extends HystrixCommand<Product> {

    private Long productId;

    public ProductInfoCommand(Long productId){
        super(HystrixCommandGroupKey.Factory.asKey("ProductInfoCommand"));
        this.productId = productId;
    }
    @Override
    protected Product run() throws Exception {
        String url = "http://127.0.0.1:9999/product/info?productId="+productId;
        String result = HttpClientUtil.doGet(url);
        Product product = JSON.parseObject(result, Product.class);
        return  product;
    }
}
