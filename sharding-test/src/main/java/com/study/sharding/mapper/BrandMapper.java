package com.study.sharding.mapper;

import com.study.entity.Brand;
import com.study.entity.ProvinceCityDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BrandMapper {
    Brand getBrandById(Long id);
}