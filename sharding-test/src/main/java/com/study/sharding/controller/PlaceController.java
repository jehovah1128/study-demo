package com.study.sharding.controller;

import com.study.entity.ProvinceCityDistrict;
import com.study.sharding.service.ProvinceCityDistrictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Resource
    private ProvinceCityDistrictService provinceCityDistrictService;

    @GetMapping("/getPlaceByPid")
    public List<ProvinceCityDistrict> getPlaceByPid(Long pid){
        if (null == pid )
            pid = 0L;
        return provinceCityDistrictService.getProvinceCityDistrictByPid(pid);
    }

    @GetMapping("/getAll")
    public List<ProvinceCityDistrict> getAll(){
        return provinceCityDistrictService.getProvinceCityDistrictAll();
    }
}
