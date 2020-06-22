package com.study.single;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
@MapperScan("com.study.single.mapper")
@ComponentScan("com.study")
@EnableTransactionManagement
@SpringBootApplication
public class SingleDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleDataApplication.class, args);
    }

}
