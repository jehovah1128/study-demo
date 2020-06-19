package com.study.sharding.service;

import com.study.entity.User;

import java.util.List;

public interface UserService {
    Long insert(String name,String phone);

    Long insertRandom();

    User selectByPrimaryKey(Long id);

    List<User> getUserList();

    boolean deleteByPrimaryKey(Long id);

    boolean updateByPrimaryKeySelective(Long id ,String name,String phone);
}
