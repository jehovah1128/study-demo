package com.study.cache.hystrix.command;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.study.common.util.HttpClientUtil;
import com.study.entity.Product;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

@Slf4j
public class ProductInfosCommand extends HystrixObservableCommand<Product> {

    private Long [] productIds;

    public ProductInfosCommand(Long [] productIds){
        super(HystrixCommandGroupKey.Factory.asKey("ProductInfoCommand"));
        this.productIds = productIds;
    }

    @Override
    protected Observable<Product> construct() {
        return Observable.create(new Observable.OnSubscribe<Product>(){

            @Override
            public void call(Subscriber<? super Product> subscriber) {
                try {
                        for (Long productId:productIds){
                            String url = "http://127.0.0.1:9999/product/info?productId="+productId;
                            String result = HttpClientUtil.doGet(url);
                            log.info("result:{}",result);
                            Product product = JSON.parseObject(result, Product.class);
                            subscriber.onNext(product);
                        }
                        subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
