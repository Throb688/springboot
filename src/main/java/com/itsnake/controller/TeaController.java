package com.itsnake.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itsnake.annotion.CheckPermission;
import com.itsnake.entity.User;
import com.itsnake.entity.Work;
import com.itsnake.entity.WorkUser;
import com.itsnake.mapper.WorkMapper;
import com.itsnake.service.WorkService;
import com.itsnake.uitls.Msg;
import com.itsnake.uitls.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/Tea")
public class TeaController {

    @Autowired
    HttpSession session;

    @Autowired
    private WorkService workService;


    @RequestMapping("/TeaPage/{pageNum}")
    @CheckPermission(value = "Tea",name = "老师页面")
    public ModelAndView TeaPage(@PathVariable("pageNum") String pageNum){

        User user = (User) session.getAttribute("user");
        Page<Work> page = workService.index(pageNum,user.getId());
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(page.getRecords());
        modelAndView.setViewName("TeaPage");
        modelAndView.addObject("page1",page);
        modelAndView.addObject("name",user.getUsername());
        return modelAndView;
    }

    @RequestMapping("/selWork")
    @ResponseBody
    @CheckPermission(value = "Tea",name = "老师查看提交作业情况")
    public Msg selWork(Integer id){
        List<WorkUser> list = workService.subWork(id);
        return Msg.success().add("mes",list);
    }

    @RequestMapping("/addWork")
    @ResponseBody
    @CheckPermission(value = "Tea",name = "老师创建作业")
    public Msg addWork(String work,String timeStr){
        User user = (User) session.getAttribute("user");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(System.currentTimeMillis());
        Work work1 = new Work();
        work1.setRid(user.getId());
        work1.setStatrtime(now);
        work1.setWorkname(work);
        work1.setLasttime(timeStr);
        work1.setCountnum(0);
        workService.addWork(work1);
        return Msg.success().add("mes","添加成功");
    }

    @RequestMapping("/delWork")
    @ResponseBody
    @CheckPermission(value = "Tea",name = "老师删除作业")
    public Msg delWork(Integer id){
        workService.delWork(id);
        return Msg.success().add("mes","删除成功");
    }

    @RequestMapping("/updWork")
    @ResponseBody
    @CheckPermission(value = "Tea",name = "老师修改作业")
    public Msg updWork(String id,String work1,String worktime1){
        Work work = new Work();
        work.setId(Integer.parseInt(id));
        work.setWorkname(work1);
        work.setLasttime(worktime1);
        workService.updWork(work);
        return Msg.success().add("mes","修改成功");
    }

    @RequestMapping("/delList")
    @ResponseBody
    @CheckPermission(value = "Tea",name = "老师批量删除")
    public Msg delList(String list){
        System.out.println(list);
        List<Integer> listID = StringUtil.StringToIntList(list);
        workService.delList(listID);
        return Msg.success().add("mes","删除成功！");
    }

    @RequestMapping("/findWork")
    @CheckPermission(value = "Tea",name = "老师查找作业")
    public ModelAndView findWork(@RequestParam("name") String  name){
        User user = (User) session.getAttribute("user");
        Page<Work> page = workService.findWork("1",user.getId(),name);
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(page.getRecords());
        modelAndView.setViewName("TeaPage");
        modelAndView.addObject("page1",page);
        modelAndView.addObject("name",user.getUsername());
        return modelAndView;
    }


}
