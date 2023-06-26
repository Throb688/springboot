package com.itsnake.controller;

import com.itsnake.entity.User;
import com.itsnake.service.UserService;
import com.itsnake.uitls.EmailUtil;
import com.itsnake.uitls.Md5Utils;
import com.itsnake.uitls.Msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/toLogin")
    @ResponseBody
    public Msg toLogin(String username, String password,String code,HttpSession session){
        String code1 = session.getAttribute("verifyCode")+"";
        if(!code.equals(code1)) return Msg.fail().add("mes","验证码错误！！");
        String pas = Md5Utils.md5(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(pas);
        User loginUser = userService.toLogin(user);
        if(ObjectUtils.isEmpty(loginUser)) return Msg.fail().add("mes","账户密码错误! !");
        session.setAttribute("user",loginUser);
        return Msg.success().add("mes","登录成功!!");
    }


    @RequestMapping("/sendEmail")
    @ResponseBody
    public Msg sendEmail(String email, HttpServletRequest httpServletRequest){
        StringBuilder builder = new StringBuilder();
        int num;
        for (int i = 0; i < 6; i++) {
            num =(int)(Math.random()*10);
            builder.append(String.valueOf(num));
        }
        String verifyCode = builder.toString();
        httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
        try {
            EmailUtil.sendMail(email,"验证信息","您的验证码为:"+verifyCode);
        } catch (Exception e) {
            return Msg.fail().add("mes", e.getMessage()+"发送失败");
        }
        return Msg.success().add("mes", "验证码发送成功!!");
    }


}
