package com.study.sharding.service.impl;

import com.study.common.util.RandomUtil;
import com.study.entity.Brand;
import com.study.sharding.mapper.BrandMapper;
import com.study.sharding.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;
    @Override
    public Brand getBrandById(Long id) throws Exception {
        int a = 0;
        if(RandomUtil.getLong()%2 == 0){
            int b = 10/a;
        }
        return brandMapper.getBrandById(id);
    }
}
