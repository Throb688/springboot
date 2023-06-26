package com.itsnake.aspect;


import com.itsnake.annotion.CheckPermission;
import com.itsnake.entity.Log;
import com.itsnake.entity.Router;
import com.itsnake.entity.User;
import com.itsnake.service.LogService;
import com.itsnake.service.RouterService;
import com.itsnake.service.UserService;
import com.itsnake.uitls.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class AuthAspect {

    @Autowired
    HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private RouterService routerService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogService logService;




    /**
     * 目标方法
     */
    @Pointcut("@annotation(com.itsnake.annotion.CheckPermission)")
    public void authPointCut(){

    }



    /**
     * 目标方法调用之前执行
     */

    /**
     * 环绕
     * 会将目标方法封装起来
     * 具体验证业务数据
     */
    @Around("authPointCut()")
    public Object authCheck(ProceedingJoinPoint pj)  {
           Long  startTime =System.currentTimeMillis();
           Log log = new Log();
            // 判断 TOKEN
           User user = (User)session.getAttribute("user");
           ModelAndView mv = new ModelAndView();
           System.out.println(user+"1122");
            if(ObjectUtils.isEmpty(user)){
                mv.setViewName("login");
               return mv;
            }
            String url = request.getRequestURL().toString();
            MethodSignature signature = (MethodSignature) pj.getSignature();
            Method method = signature.getMethod();
            CheckPermission appleAnnotation = method.getAnnotation(CheckPermission.class);
            String methodAccess= appleAnnotation.value();
            String methName = appleAnnotation.name();
            List<Router> rouList = routerService.findByRouter(StringUtil.StringToIntList(
                userService.findByRouterName(user.getId())));
            boolean flag = false;
            for (int i =0;i<rouList.size();i++){
                if(rouList.get(i).getUrl().indexOf(methodAccess)>-1){
                    flag = true;
                    break;
                }
            }
           if(flag){
               try {
                   return pj.proceed();
               } catch (Throwable throwable) {
                   throwable.printStackTrace();
               }finally {
                   Date day=new Date();
                   SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   String time = sdf.format(day);
                   log.setUsername(user.getUsername());
                   log.setControllerurl(url);
                   log.setOperation(methName);
                   log.setTimedate(time);
                   long E_time = System.currentTimeMillis() - startTime;
                   log.setKeeptime(E_time+"");
                   logService.save(log);
               }
           }
           mv.setViewName("error");
           return mv;
    }



}
