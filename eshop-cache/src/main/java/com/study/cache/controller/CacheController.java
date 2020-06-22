package com.study.cache.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.study.cache.hystrix.command.ProductInfoCommand;
import com.study.cache.hystrix.command.ProductInfosCommand;
import com.study.common.util.HttpClientUtil;
import com.study.common.util.IdsToArray;
import com.study.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RestController
public class CacheController {

    @RequestMapping("/cache/change")
    public Product productIsChange(Long productId){
        ProductInfoCommand productInfoCommand = new ProductInfoCommand(productId);
        Product product = productInfoCommand.execute();
        return product;
    }

    @RequestMapping("/cache/getInfos")
    public Map<Long,Product> getProductInfos(String productIds){
//        CountDownLatch latch = new CountDownLatch(1);
        Long[] ids = IdsToArray.idsToLongArray(productIds, IdsToArray::idsToLongArrayByDefaultRegex);
        Map<Long,Product> map = new HashMap<>();
        log.info("ids length:{}",ids.length);
        ProductInfosCommand productInfosCommand = new ProductInfosCommand(ids);
        Observable observable = productInfosCommand.observe();
        observable.subscribe(new Observer<Product>() {
            @Override
            public void onCompleted() {
                log.info("get product info all do success");
//                latch.countDown();
            }

            @Override
            public void onError(Throwable e) {
                log.error("get product info error", e);
            }

            @Override
            public void onNext(Product product) {
                log.info("get product info success id:{}",product.getId());
                map.put(product.getId(),product);
            }

        });
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            log.info("count down latch await error",e);
//        }
        return map;
    }
}
