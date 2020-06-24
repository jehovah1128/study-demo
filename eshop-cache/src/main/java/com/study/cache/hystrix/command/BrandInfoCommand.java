package com.study.cache.hystrix.command;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.*;
import com.study.common.util.HttpClientUtil;
import com.study.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BrandInfoCommand extends HystrixCommand<Brand> {

    private static Map<Long,Brand> map = new HashMap<>();

    private Long brandId;

    private static final String BRAND_INFO_URL= "http://192.168.1.15:9999/brand/info?brandId=";

    public static final HystrixCommandKey COMMAND_KEY=HystrixCommandKey.Factory.asKey("BrandInfoCommand");

    public BrandInfoCommand(Long brandId){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BrandInfoGroup"))
                .andCommandKey(COMMAND_KEY)
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withRequestCacheEnabled(false)
                        .withFallbackEnabled(true)
                )
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("getBrandInfoPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withMaxQueueSize(20)
                ));
        this.brandId = brandId;
    }

    @Override
    protected Brand run() throws Exception {
        String result = HttpClientUtil.doGet(BRAND_INFO_URL+brandId);
        if (!StringUtils.isEmpty(result)){
            Brand brand = JSON.parseObject(result, Brand.class);
            map.put(brandId, brand);
            return brand;
        }
        throw  new  Exception("我查不到数据啊");
    }

    @Override
    protected Brand getFallback() {
        log.info("查不到数据啊,只能从之前的记录中找一个备用");
        return map.get(brandId);
    }
}
