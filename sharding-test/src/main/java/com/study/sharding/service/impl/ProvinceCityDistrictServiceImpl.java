package com.study.sharding.service.impl;

import com.study.entity.ProvinceCityDistrict;
import com.study.sharding.mapper.ProvinceCityDistrictMapper;
import com.study.sharding.service.ProvinceCityDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ProvinceCityDistrictServiceImpl implements ProvinceCityDistrictService {

    @Resource
    private ProvinceCityDistrictMapper provinceCityDistrictMapper;


    @Override
    public List<ProvinceCityDistrict> getProvinceCityDistrictByPid(
            Long pid) {
        return provinceCityDistrictMapper.getProvinceCityDistrictByPid(pid);
    }

    @Override
    public List<ProvinceCityDistrict> getProvinceCityDistrictAll() {
        return provinceCityDistrictMapper.getProvinceCityDistrictAll();
    }


}
