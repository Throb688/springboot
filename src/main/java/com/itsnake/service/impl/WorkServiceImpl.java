package com.itsnake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itsnake.entity.Work;
import com.itsnake.entity.WorkUser;
import com.itsnake.mapper.WorkMapper;
import com.itsnake.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    private WorkMapper workMapper;

    public Page<Work>  index(String page1,Integer id){
        Page<Work> page = new Page<>(Integer.parseInt(page1),5);
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",id);
        workMapper.selectPage(page,queryWrapper);
        return page;
    }

    public List<WorkUser> subWork(Integer id){
        return workMapper.selWork(id);
    }

    public void addWork(Work work){
        workMapper.insert(work);
    }

    public void delWork(Integer id){
        workMapper.deleteById(id);
    }

    public void updWork(Work work){
        workMapper.updateById(work);
    }

    public void delList(List<Integer> list){
        workMapper.deleteBatchIds(list);
    }

    public Page<Work>  findWork(String page1,Integer id,String workName){
        Page<Work> page = new Page<>(Integer.parseInt(page1),5);
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",id).like("workname",workName);
        workMapper.selectPage(page,queryWrapper);
        return page;
    }

    public Page<Work> StuIndex(String page1){
        Page<Work> page = new Page<>(Integer.parseInt(page1),5);
        workMapper.selectPage(page,null);
        return page;
    }

    @Override
    public Integer findWorkStatus(Integer wid, String username) {
        return workMapper.findWorkStatus(wid,username);
    }

    @Override
    public void subWork(String id, String username, String worktext, String timedate) {
        workMapper.subWork(Integer.parseInt(id),username,worktext,timedate);
    }


}
