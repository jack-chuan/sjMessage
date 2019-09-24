package cn.com.sparknet.sjMessage.app.controller.system;

import cn.com.sparknet.sjMessage.app.entity.system.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

/**
 * @program: fast
 * @description: Login
 * @author: Leo Lee
 * @create: 2018-09-28 00:32
 **/

@Controller
@RequestMapping("/auth")
public class LoginController {

    /**
    * @Description: login 用户登录接口
    * @Param: [sysUser, request]
    * @return: java.lang.String
    * @Author: Leo Lee
    * @Date: 18.9.28
    */
    @PostMapping("/login")
    public String login(SysUser sysUser, HttpServletRequest request){

        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            request.getSession().setAttribute("sysUser", sysUser);
            return "system/success";
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("sysUser", sysUser);
            request.getSession().setAttribute("error", "用户名或密码错误");
            return "/system/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "system/login";
    }

    /**
    * @Description: admin 身份认证测试接口
    * @Param: [request]
    * @return: java.lang.String
    * @Author: Leo Lee
    * @Date: 18.9.28
    */
    @RequestMapping("/admin")
    public String admin(HttpServletRequest request){
        Object sysUser = request.getSession().getAttribute("sysUser");
        return "system/success";
    }

    /**
    * @Description: manager 角色认证测试接口
    * @Param: [request]
    * @return: java.lang.String
    * @Author: Leo Lee
    * @Date: 18.9.28
    */
    @RequestMapping("/manager")
    public String manager(HttpServletRequest request){
        return "system/success";
    }
    
    /** 
    * @Description: guest 权限认证测试接口
    * @Param: [request] 
    * @return: java.lang.String 
    * @Author: Leo Lee
    * @Date: 18.9.28 
    */
    @RequestMapping("/guest")
    public String guest(HttpServletRequest request){
        return "system/success";
    }


}
