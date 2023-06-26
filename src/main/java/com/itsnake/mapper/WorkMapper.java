package com.itsnake.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itsnake.entity.Work;
import com.itsnake.entity.WorkUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkMapper extends BaseMapper<Work> {

    @Select("SELECT * FROM work_user WHERE wid =#{id}")
    List<WorkUser> selWork(Integer id);

    @Select("SELECT count(*) FROM work_user WHERE wid=#{wid} and username=#{username}")
    Integer findWorkStatus(Integer wid,String username);

    @Select("INSERT INTO work_user(wid,username,worktext,subtime) VALUES(#{id},#{username},#{worktext},#{timedate})")
    void subWork(Integer id,String username,String worktext,String timedate);
}
