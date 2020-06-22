package com.study.sharding.service;

import com.study.entity.Product;

public interface ProductService {
    Long addProduct(String name,String price,String[] pictures,String specification,String service,String[] colors,String size,Long shopId);
    boolean updateProduct(Long id,String name,String price,String[] pictures,String specification,String service,String[] colors,String size);
    Product getProductById(Long id);
}
