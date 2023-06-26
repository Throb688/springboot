package com.itsnake.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itsnake.entity.Log;
import com.itsnake.mapper.LogMapper;
import com.itsnake.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
}
