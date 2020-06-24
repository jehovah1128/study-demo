package com.study.sharding.service;

import com.study.entity.Product;
import com.study.vo.ProductVo;

public interface ProductService {
    Long addProduct(String name,String price,String[] pictures,String specification,String service,String[] colors,String size,Long shopId);
    boolean updateProduct(Long id,String name,String price,String[] pictures,String specification,String service,String[] colors,String size);
    ProductVo getProductById(Long id);
}
