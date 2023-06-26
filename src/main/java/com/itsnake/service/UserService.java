package com.itsnake.service;

import com.itsnake.entity.User;

public interface UserService {

    User toLogin(User user);
    boolean updPas(User user);
    String findByRoleName(Integer id);
    String findByRouterName(Integer id);
    String findUserName(Integer id);
}
