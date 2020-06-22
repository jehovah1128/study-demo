package com.study.single.mapper;

import com.study.entity.Product;

public interface ProductMapper {
    int insert(Product product);

    Product selectById(Long id);

    int updateProduct(Product product);

}
