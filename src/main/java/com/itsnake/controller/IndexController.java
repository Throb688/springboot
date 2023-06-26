package com.itsnake.controller;

import com.itsnake.entity.Router;
import com.itsnake.entity.User;
import com.itsnake.service.RouterService;
import com.itsnake.service.UserService;
import com.itsnake.uitls.Md5Utils;
import com.itsnake.uitls.Msg;
import com.itsnake.uitls.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private RouterService routerService;

    @Autowired
    HttpSession session;

    @RequestMapping("/index")
    public ModelAndView indexPage(){
        User user = (User) session.getAttribute("user");
        String roleName = userService.findByRoleName(user.getId());
        List<Router> rouList = routerService.findByRouter(StringUtil.StringToIntList(
                userService.findByRouterName(user.getId())));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("userName",user.getUsername());
        mv.addObject("roleName",roleName);
        mv.addObject("rouList",rouList);
        return mv;
    }




    @RequestMapping("/updPasPage")
    public String updPasPage(){
        return "updPas";
    }




    @RequestMapping("/updPas")
    @ResponseBody
    public Msg updPas(String pass,String rePa){
        User user = (User) session.getAttribute("user");
        System.out.println(user.toString());
        if(!Md5Utils.md5(pass).equals(user.getPassword())) return Msg.fail().add("mes","原密码不一致");
        user.setPassword(Md5Utils.md5(rePa));
        boolean flag = userService.updPas(user);
        if(flag) return Msg.success().add("mes","密码修改成功！！");
        return Msg.fail().add("mes","密码修改失败！！");
    }


}
