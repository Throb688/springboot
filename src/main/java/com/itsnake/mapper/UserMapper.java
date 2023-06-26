package com.itsnake.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itsnake.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT roleName FROM role WHERE id = (SELECT rid FROM user_role WHERE uid =#{id})")
    String findByRoleName(Integer uid);

    @Select("SELECT router_id FROM router_role WHERE rid = (SELECT id FROM role WHERE id = (SELECT rid FROM user_role WHERE uid =#{id}))")
    String findByRouterName(Integer uid);
}
