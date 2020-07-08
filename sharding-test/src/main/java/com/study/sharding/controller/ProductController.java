package com.study.sharding.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.entity.Product;
import com.study.entity.ProvinceCityDistrict;
import com.study.sharding.service.ProductService;
import com.study.sharding.service.ProvinceCityDistrictService;
import com.study.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping("/info")
    public ProductVo getProductInfo(Long productId){
        ProductVo vo = productService.getProductById(productId);
        log.info("get vo:{}", JSONObject.toJSONString(vo));
        return vo;
    }

    @PostMapping(value = "/add")
    public Long addProduct(String name,String price,String[] pictures,String specification,String service,String[] colors,String size,Long shopId){
        log.info("name:{},price:{},pictures:{}",name,price,pictures);
        return productService.addProduct(name, price, pictures, specification, service, colors, size, shopId);
    }

    @PutMapping("/modify")
    public boolean addProduct(Long id,String name,String price,String[] pictures,String specification,String service,String[] colors,String size){
        return productService.updateProduct(id,name, price, pictures, specification, service, colors, size);
    }


}
