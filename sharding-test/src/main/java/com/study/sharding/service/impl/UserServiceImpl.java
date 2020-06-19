package com.study.sharding.service.impl;

import com.study.common.NameUtil;
import com.study.common.PhoneUtil;
import com.study.common.SnowFlakeUtil;
import com.study.entity.Balance;
import com.study.entity.User;
import com.study.sharding.mapper.BalanceMapper;
import com.study.sharding.mapper.UserMapper;
import com.study.sharding.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private BalanceMapper balanceMapper;
    @Resource
    private SnowFlakeUtil snowFlakeUtil;
    @Resource
    private NameUtil nameUtil;
    @Resource
    private PhoneUtil phoneUtil;

    @Override
    public Long insert(String name, String phone) {
        Long id = snowFlakeUtil.nextId();
        User user = new User(id, name, phone, new Date());
        if (userMapper.insert(user) > 0){
            Balance balance = new Balance(snowFlakeUtil.nextId(),id,new BigDecimal(new Random().nextInt(1000)),new Date(),null);
            balanceMapper.insert(balance);
            return id;
        }

        return null;
    }
    @Override
    public Long insertRandom() {
        Long id = snowFlakeUtil.nextId();
        String name = nameUtil.getName();
        String phone = phoneUtil.getPhone();
        User user = new User(id, name, phone,null);
        if (userMapper.insert(user) > 0){
            Balance balance = new Balance(snowFlakeUtil.nextId(),id,new BigDecimal(new Random().nextInt(1000)),null,null);
            balanceMapper.insert(balance);
            return id;
        }

        return null;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Long id, String name, String phone) {
        User user = new User(id, name, phone, null);
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }


}
