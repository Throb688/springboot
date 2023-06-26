package com.itsnake.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itsnake.entity.Work;
import com.itsnake.entity.WorkUser;

import java.util.List;

public interface WorkService  {

    Page<Work>  index(String page1,Integer id);
    List<WorkUser> subWork(Integer id);
    void addWork(Work work);
    void delWork(Integer id);
    void updWork(Work work);
    void delList(List<Integer> list);
    Page<Work> findWork(String page1,Integer id,String workName);
    Page<Work> StuIndex(String page1);
    Integer findWorkStatus(Integer wid,String username);
    void subWork(String id,String username,String worktext,String timedate);
}
