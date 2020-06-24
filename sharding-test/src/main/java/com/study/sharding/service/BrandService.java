package com.study.sharding.service;

import com.study.entity.Brand;

public interface BrandService {
    Brand getBrandById(Long id) throws Exception;
}
