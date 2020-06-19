package com.study.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    static Random random = new Random();
    public static Long getLong(){
        return Long.valueOf(random.nextInt(10));
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList(100);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
    }
}
