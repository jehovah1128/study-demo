package com.study.sharding.controller;

import com.study.entity.Product;
import com.study.sharding.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping("/info")
    public Product getProductInfo(@RequestHeader("productId") Long productId){
        return productService.getProductById(productId);
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
