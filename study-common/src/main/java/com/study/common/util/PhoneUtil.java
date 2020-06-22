package com.study.common.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PhoneUtil {
    Random r = new Random();
    public String getPhone(){
        StringBuffer sb = new StringBuffer("1");
        sb.append(getFiveNum());
        sb.append(getFiveNum());
        return sb.toString();
    }
    public int getFiveNum(){
        return  r.nextInt(89999) + 10000;
    }
}
