package com.itsnake.service.impl;

import com.itsnake.entity.Router;
import com.itsnake.mapper.RouterMapper;
import com.itsnake.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouterServiceImpl implements RouterService {

    @Autowired
    private RouterMapper routerMapper;

    public List<Router> findByRouter(List<Integer> idList){
        return routerMapper.selectBatchIds(idList);
    }
}
