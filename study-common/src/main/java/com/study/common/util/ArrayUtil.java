package com.study.common.util;

public class ArrayUtil {

    private static final String default_regex = ",";

    public static <T> String arrayToStringDefault(T[] array){
        return arrayToString(array, default_regex);
    }

    public  static <T> String arrayToString(T[] array, String regex){
        if (null != array && array.length > 0){
            StringBuffer sb = new StringBuffer();
            for (T t:array){
                sb.append(t);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        }
        return null;
    }

}
