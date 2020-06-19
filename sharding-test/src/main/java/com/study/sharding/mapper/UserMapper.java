package com.study.sharding.mapper;

import com.study.entity.User;

import java.util.List;

public interface UserMapper {
    int insert(User user);

    User selectByPrimaryKey(Long id);

    List<User> getUserList();

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User user);
}
