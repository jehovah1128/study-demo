package com.study.sharding.mapper;

import com.study.entity.ProvinceCityDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProvinceCityDistrictMapper {

    List<ProvinceCityDistrict> getProvinceCityDistrictByPid(@Param("pid") Long pid);

    List<ProvinceCityDistrict> getProvinceCityDistrictAll();

    String getProvinceCityDistrictString(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId, @Param("districtId") Long districtId);
}