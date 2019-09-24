package cn.com.sparknet.sjMessage.app.controller.system;

import cn.com.sparknet.sjMessage.common.util.WaterUtil;
import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainUserEntity;
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
 * @author: YangQr
 * @create: 2019-05-27 16:20
 **/

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    /**
    * @Description: login 用户登录接口
    * @Param: [sysUser, request]
    * @return: java.lang.String
    * @Author: YangQr
    * @Date: 19.5.27
    */
    @PostMapping("/login")
    public String login(MsMainUserEntity msMainUserEntity, HttpServletRequest request){

        String username = msMainUserEntity.getUsername();
        String password = msMainUserEntity.getPassword();

        Subject subject = SecurityUtils.getSubject();

        try {
            String pwdDark = WaterUtil.getCipherText(password, "");
            UsernamePasswordToken token = new UsernamePasswordToken(username, pwdDark);
            subject.login(token);
            request.getSession().setAttribute("sysUser", msMainUserEntity);
            return "system/success";
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("sysUser", msMainUserEntity);
            request.getSession().setAttribute("error", "用户名或密码错误");
            return "/system/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "system/login";
    }
}
