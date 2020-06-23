package com.study.sharding.service;

import com.study.entity.ProvinceCityDistrict;

import java.util.List;

public interface ProvinceCityDistrictService {
    List<ProvinceCityDistrict> getProvinceCityDistrictByPid(Long pid);
    List<ProvinceCityDistrict> getProvinceCityDistrictAll();
}
