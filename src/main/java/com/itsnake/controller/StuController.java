package com.itsnake.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itsnake.annotion.CheckPermission;
import com.itsnake.entity.User;
import com.itsnake.entity.Work;
import com.itsnake.service.UserService;
import com.itsnake.service.WorkService;
import com.itsnake.uitls.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/Stu")
public class StuController {

    @Autowired
    HttpSession session;

    @Autowired
    private WorkService workService;

    @Autowired
    private UserService userService;


    @RequestMapping("/writeWork/{pageNum}")
    @CheckPermission(value = "Stu",name = "学生查看作业")
    public ModelAndView writeWork(@PathVariable("pageNum") String pageNum){
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView();
        Page<Work> page = workService.StuIndex(pageNum);
        List<Work> pageRecords = page.getRecords();
        for (int i = 0;i<pageRecords.size();i++){
            Work work = pageRecords.get(i);
            work.setTeaname(userService.findUserName(work.getRid()));
            if(workService.findWorkStatus(work.getId(),user.getUsername())>0){
                work.setStatus(true);
            }else {
                work.setStatus(false);
            }
        }
        mv.setViewName("writeWork");
        mv.addObject("list",pageRecords);
        mv.addObject("page1",page);
        return mv;
    }

    @RequestMapping("/stuWork")
    @ResponseBody
    @CheckPermission(value = "Stu",name = "学生提交作业")
    public Msg stuWork(String id,String worktext){
        User user = (User) session.getAttribute("user");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(System.currentTimeMillis());
        workService.subWork(id,user.getUsername(),worktext,now);
        return Msg.success().add("mes","提交成功");
    }

}
