package com.study.common.util;

import com.study.common.funtions.IdStringToLongArray;

public class IdsToArray {
    private static  final String default_regex = ",";
    public static Long[] idsToLongArray(String ids, IdStringToLongArray idStringToLongArray){
        return idStringToLongArray.idsToArray(ids);
    }

    public static Long [] idsToLongArrayByDefaultRegex(String ids) {
        return idsToLongArrayByRegex(ids,default_regex);
    }

    public static Long [] idsToLongArrayByRegex(String ids,String regex){
        String [] idStrs = ids.split(regex);
        Long [] idls = new Long[idStrs.length];
        for (int i = 0; i < idStrs.length; i++) {
            idls[i] = Long.valueOf(idStrs[i]);
        }
        return idls;
    }
}
