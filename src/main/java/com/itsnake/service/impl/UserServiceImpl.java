package com.itsnake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itsnake.entity.User;
import com.itsnake.mapper.UserMapper;
import com.itsnake.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User toLogin(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername())
                .eq("password",user.getPassword());
        return userMapper.selectOne(queryWrapper);
    }


    @Override
    public boolean updPas(User user){
       int flag = userMapper.updateById(user);
       return flag>0;
    }

    public String findByRoleName(Integer id){
        return userMapper.findByRoleName(id);
    }

    public String findByRouterName(Integer id){
        return userMapper.findByRouterName(id);
    }

    public String findUserName(Integer id){
        return userMapper.selectById(id).getUsername();
    }
}
