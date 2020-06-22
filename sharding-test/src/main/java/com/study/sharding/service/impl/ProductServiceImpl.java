package com.study.sharding.service.impl;

import com.study.common.util.ArrayUtil;
import com.study.common.util.SnowFlakeUtil;
import com.study.entity.Product;
import com.study.sharding.mapper.ProductMapper;
import com.study.sharding.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private SnowFlakeUtil snowFlakeUtil;

    @Override
    public Long addProduct(String name, String price, String[] pictures, String specification, String service, String[] colors, String size, Long shopId) {
        Long nowTimeL = System.currentTimeMillis();
        log.info("-----0-------");
        Product product  = new Product();
        product.setId(snowFlakeUtil.nextId());
        product.setName(name);
        product.setPrice(new BigDecimal(price));
        product.setPictureList(ArrayUtil.arrayToStringDefault(pictures));
        product.setSpecification(specification);
        product.setService(service);
        product.setColor(ArrayUtil.arrayToStringDefault(colors));
        product.setSize(size);
        product.setShopId(shopId);
        log.info(System.currentTimeMillis()-nowTimeL+"");
        if (productMapper.insert(product) > 0){
            log.info(System.currentTimeMillis()-nowTimeL+"");
            return product.getId();
        }
        return null;
    }

    @Override
    public boolean updateProduct(Long id, String name, String price, String[] pictures, String specification, String service, String[] colors, String size) {
            Product product  = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(new BigDecimal(price));
            product.setPictureList(ArrayUtil.arrayToStringDefault(pictures));
            product.setSpecification(specification);
            product.setService(service);
            product.setColor(ArrayUtil.arrayToStringDefault(colors));
            product.setSize(size);
        return productMapper.updateProduct(product) > 0;
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }
}
