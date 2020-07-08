package com.study.cache.hystrix.command;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.*;
import com.study.common.util.HttpClientUtil;
import com.study.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BrandInfoCommand extends HystrixCommand<Brand> {

    private static Map<Long,Brand> map = new HashMap<>();

    private Long brandId;

    private static final String BRAND_INFO_URL= "http://192.168.1.15:9999/brand/info?brandId=";

    public static final HystrixCommandKey COMMAND_KEY=HystrixCommandKey.Factory.asKey("BrandInfoCommand");

    public BrandInfoCommand(Long brandId){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BrandInfoGroup"))   //设置command组
                .andCommandKey(COMMAND_KEY)  // 设置command  key
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()   //设置command属性
                        .withRequestCacheEnabled(false)  // 是否开始请求缓存
                        .withFallbackEnabled(true) //是否开启降级
                   //     .withCircuitBreakerEnabled(true)   // 是否允许短路器工作   默认允许
                        .withCircuitBreakerRequestVolumeThreshold(30)   //设置短路器窗口期最低访问阈值
                        .withCircuitBreakerSleepWindowInMilliseconds(10000)  //设置短路器短路开启后  持续时间
                        .withCircuitBreakerErrorThresholdPercentage(40)   //打开短路器的异常比例   40%

                )
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("getBrandInfoPool"))  // 设置command所属线程池
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()   // 设置线程池属性
                        .withCoreSize(15)    // 设置核心线程数量
                        .withMaxQueueSize(20)   // 设置最大队列数量
                        .withQueueSizeRejectionThreshold(10)
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
//        log.info("查不到数据啊,只能从之前的记录中找一个备用");
        return new Brand(1L,"降级商品品牌",new Date());
    }
}
