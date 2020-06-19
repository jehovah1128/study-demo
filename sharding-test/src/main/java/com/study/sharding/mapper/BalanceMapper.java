package com.study.sharding.mapper;

import com.study.entity.Balance;

public interface BalanceMapper {
    int insert(Balance balance);

    Balance selectByPrimaryKey(Long id);

}
