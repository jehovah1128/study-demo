package com.study.sharding.controller;

import com.study.sharding.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/add")
    public Object insertUser(String name,String phone){
        return userService.insert(name, phone);
    }
    @RequestMapping("/adds")
    public Object insertUser(Integer addNum){
        List<Long> list = new ArrayList<>();
        for (int i= 0;i<addNum;i++){
            list.add(userService.insertRandom());
        }
        return list;
    }

    @RequestMapping("/get")
    public Object getUser(Long id){
        return  userService.selectByPrimaryKey(id);
    }
    @RequestMapping("/list")
    public Object getUserList(){
        return userService.getUserList();
    }
    @RequestMapping("/update")
    public Object updateUser(Long id ,String name,String phone){
        return userService.updateByPrimaryKeySelective(id, name, phone);
    }
    @RequestMapping("/delete")
    public Object deleteUser(Long id){
        return userService.deleteByPrimaryKey(id);
    }
}
