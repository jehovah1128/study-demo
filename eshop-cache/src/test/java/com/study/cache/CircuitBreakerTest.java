package com.study.cache;

import com.study.common.util.HttpClientUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CircuitBreakerTest {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
        for (int i = 0; i < 100; i++) {
            if (i == 0){
                System.out.println("time--"+sdf.format(new Date()));
            }
            String result = HttpClientUtil.doGet("http://192.168.1.15:9998/cache/brand?brandId=4444445555556576984");
            System.out.println("第" + (i + 1) + "次请求,结果:" + result);
            if (i % 5 == 4) {
                Thread.sleep(1500);
                System.out.println("time--"+sdf.format(new Date()));
            }
        }
    }
}
