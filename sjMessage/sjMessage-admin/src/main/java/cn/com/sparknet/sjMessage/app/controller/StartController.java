package cn.com.sparknet.sjMessage.app.controller;

import cn.com.sparknet.sjMessage.app.entity.User;
import cn.com.sparknet.sjMessage.common.exception.BusinessErrorException;
import cn.com.sparknet.sjMessage.common.exception.BusinessMsgEnum;
import cn.com.sparknet.sjMessage.common.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@Api(value = "四大基础注解api", tags = "四种基础注解Api")
@RestController
@EnableAutoConfiguration
public class StartController {

    private final static Logger logger = LoggerFactory.getLogger(StartController.class);

    /**
     * @author leolee
     * @date 2018-08-07 21:08
     * @param flag
     * @param pathValue
     * @return java.lang.String
    */
    @ApiOperation(value = "RM及PV注解", notes = "@RequestMapping, @PathVariable 有参数及无参数")
    @RequestMapping(value = "/start/{flag}/{value}", method = RequestMethod.GET)
    public String start(@PathVariable @ApiParam(value = "PV 无参数") String flag,
                        @PathVariable(value = "value") @ApiParam(value = "PV 有参数") String pathValue){
        return "Spring-boot start..." + flag + ", pathValue: " + pathValue;
    }

    /**
     * @author leolee
     * @date 2018-08-07 21:10
     * @param
     * @return
    */
    @ApiOperation(value = "RM及RP注解", notes = "@RequestMapping, @RequestParam 传参")
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startParm(@RequestParam @ApiParam(value = "RequestParam '?='") String param){
        return "Spring-boot start - param: " + param;
    }

    /**
     * @author leolee
     * @date 2018-08-07 21:12
     * @param user
     * @return
    */
    @ApiOperation(value = "RB注解", notes = "@RequestBody 接受JSON请求参数")
    @PostMapping("/start/post")
    public String startPost(@RequestBody @ApiParam(value = "RB 接受JSON请求参数") User user){
        String userInfo = "user info: id - " + user.getUserId() + ", name - " + user.getUserName()
//                + ", passwoed - " + user.getPassword() + ", phone - " + user.getPhone();
                + ", passwoed - " + user.getPassword();

        logger.info(userInfo);
        return userInfo;
    }
    
    /**
     * @author leolee
     * @date 2018-08-07 21:14
     * @param
     * @return
    */
    @ApiOperation(value = "logger功能", notes = "SLF4J下Logger及LoogerFactory，Log打印及返回值api")
    @RequestMapping(value = "/start/log", method = RequestMethod.GET)
    public String startLog(){
        logger.debug("---- debug log ----");
        logger.info("---- info log ----");
        logger.error("---- error log ----");
        logger.warn("---- warn log ----");

        // other info
        String str1 = "fast.pip3.com";
        String str2 = "fast.pip3.com/log";
        logger.info("---- leo_fast - {} - {} ----", str1, str2);

        return "success";
    }

    /**
     * 异常处理示例
     * @author leolee
     * @date 2018-08-08 13:56
     * @param name String
     * @param pass String
     * @return JsonResult
    */
    @ApiOperation(value = "异常处理3", notes = "缺少请求参数的异常处理")
    @RequestMapping(value = "/start/ex", method = RequestMethod.POST)
    public JsonResult startEx(@RequestParam("name") String name,
                              @RequestParam("pass") String pass){
        logger.info("name: {}", name);
        logger.info("pass: {}", pass);
        return new JsonResult();
    }

    /**
     * 自定义异常处理示例
     * @author leolee
     * @date 2018-08-08 14:38
     * @param
     * @return JsonResult
    */
    @ApiOperation(value = "异常处理X", notes = "自定义业务的异常处理")
    @RequestMapping(value = "/start/exself", method = RequestMethod.GET)
    public JsonResult startExSelf(){
        try {
            int i = 1/0;
        } catch (Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return new JsonResult();
    }
}
