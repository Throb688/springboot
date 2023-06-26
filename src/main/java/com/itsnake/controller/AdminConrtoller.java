package com.itsnake.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itsnake.annotion.CheckPermission;
import com.itsnake.entity.Log;
import com.itsnake.entity.User;
import com.itsnake.entity.Work;
import com.itsnake.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminConrtoller {

    @Autowired
    HttpSession session;

    @Autowired
    private LogService logService;

    @RequestMapping("/log/{pageNum}")
    @CheckPermission(value = "admin",name = "查看日志")
    public ModelAndView writeWork(@PathVariable("pageNum") String pageNum){
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView();
        Page<Log> page = new Page<>(Integer.parseInt(pageNum) ,5);
        logService.page(page,null);
        List<Log> pageRecords = page.getRecords();
        mv.setViewName("log");
        mv.addObject("list",pageRecords);
        mv.addObject("page1",page);
        return mv;
    }
}
