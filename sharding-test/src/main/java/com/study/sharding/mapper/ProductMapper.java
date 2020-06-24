package com.study.sharding.mapper;

import com.study.entity.Balance;
import com.study.entity.Product;
import com.study.vo.ProductVo;

public interface ProductMapper {
    int insert(Product product);

    Product selectById(Long id);

    ProductVo selectVoById(Long id);

    int updateProduct(Product product);

}
