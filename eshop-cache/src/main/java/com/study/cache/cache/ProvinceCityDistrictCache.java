package com.study.cache.cache;

import com.alibaba.fastjson.JSON;
import com.study.common.util.HttpClientUtil;
import com.study.entity.ProvinceCityDistrict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ProvinceCityDistrictCache  {
    public static Map<Long, List<ProvinceCityDistrict>> spaceCache = new HashMap<>();
    public static Map<Long,ProvinceCityDistrict> districtCache = new HashMap<>();
    private static final String url = "http://192.168.1.15:9999/place/getAll";
    public void init(){
        log.info("begin do init province city district");
        String result = HttpClientUtil.doGet(url);
        if (!StringUtils.isEmpty(result)){
            List<ProvinceCityDistrict> allSpace = JSON.parseArray(result, ProvinceCityDistrict.class);
            allSpace.forEach(f -> districtCache.put(f.getId(), f));
            doInitCache(0L, allSpace, 0);
            log.info("success do init province city district");
        }else{
            log.info("province city district init error:get result is empty");
        }
    }
    void doInitCache(Long pid,List<ProvinceCityDistrict> allSpace,int level){
        List<ProvinceCityDistrict> placeList = allSpace.stream().filter(p -> p.getPid().equals(pid)).collect(Collectors.toList());
        spaceCache.put(pid,placeList);
        if (placeList.size()>0 && level < 2){
            placeList.forEach(l-> doInitCache(l.getId(),allSpace, level +1));
        }
    }

    public static List<ProvinceCityDistrict> getProvinceCityDistrictByPid(Long pid){
        if (null == pid)
            pid = 0L;
        return spaceCache.get(pid);
    }
    public static String getName(Long id){
        if (null == id)
            return null;
        ProvinceCityDistrict p = districtCache.get(id);
        return null == p ? null:p.getName();
    }


}
