package com.study.sharding.controller;

import com.study.entity.Brand;
import com.study.sharding.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    @RequestMapping("/info")
    public Brand getBrandInfo(Long brandId) throws Exception {
        return brandService.getBrandById(brandId);
    }
}
