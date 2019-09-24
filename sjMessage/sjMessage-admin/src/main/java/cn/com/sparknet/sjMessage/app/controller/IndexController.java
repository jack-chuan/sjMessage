package cn.com.sparknet.sjMessage.app.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(value = "thymeleaf静态页面", tags = "thymeleaf静态页面")
public class IndexController {


    /**
     * @author leolee
     * @date 2018-08-07 21:06
     * @param request
     * @return java.lang.String
     * @throws 
     * @since 
    */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "index模板", notes = "访问templates中的thymeleaf静态页面测试")
    public String index(HttpServletRequest request){
        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "system/login";
    }

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "system/unauthorized";
    }
}
