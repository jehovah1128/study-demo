package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceCityDistrict implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6572052600307605565L;

    private Long id;

    private Long pid;

    private String name;

}