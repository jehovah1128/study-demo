package com.study.cache;

import com.study.cache.filter.HystrixRequestContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EshopCacheApplication {


    @Bean
    public FilterRegistrationBean hystrixRequestContextFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new HystrixRequestContextFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(EshopCacheApplication.class, args);
    }

}
