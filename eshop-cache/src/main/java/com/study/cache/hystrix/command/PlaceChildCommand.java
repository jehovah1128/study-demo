package com.study.cache.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.study.cache.cache.ProvinceCityDistrictCache;
import com.study.entity.Product;
import com.study.entity.ProvinceCityDistrict;

import java.util.List;

public class PlaceChildCommand extends HystrixCommand<List<ProvinceCityDistrict>> {

    private Long pid;

    protected PlaceChildCommand(Long pid) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("getPlaceGroup"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.pid = pid;
    }

    @Override
    protected List<ProvinceCityDistrict> run() throws Exception {
        return ProvinceCityDistrictCache.getProvinceCityDistrictByPid(pid);
    }
}
