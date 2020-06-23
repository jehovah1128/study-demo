package com.study.cache.init;

import com.study.cache.cache.ProvinceCityDistrictCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CacheInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        new ProvinceCityDistrictCache().init();
    }
}
