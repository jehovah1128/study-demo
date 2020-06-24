package com.study.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    private String pictureList;

    private String specification;

    private String service;

    private String color;

    private String size;

    private Long shopId;

    private String shopName;

    private Long cityId;

    private String cityName;

    private Long brandId;

    private String brandName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

}
