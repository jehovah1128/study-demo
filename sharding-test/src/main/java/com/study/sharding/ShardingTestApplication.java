package com.study.sharding;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
@MapperScan("com.study.sharding.mapper")
@ComponentScan("com.study")
@EnableTransactionManagement
@PropertySource("classpath:application-sharding.yml")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShardingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingTestApplication.class, args);
    }

}
