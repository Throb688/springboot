package com.itsnake.service;

import com.itsnake.entity.Router;

import java.util.List;

public interface RouterService {

    List<Router> findByRouter(List<Integer> idList);
}
