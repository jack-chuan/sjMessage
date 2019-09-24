package cn.com.sparknet.sjMessage.app.controller;

import cn.com.sparknet.sjMessage.app.entity.User;
import cn.com.sparknet.sjMessage.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
@Api(value = "mybatis 实体类映射及相关信息查询", tags = "用户操作接口")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author leolee
     * @date 2018-08-07 21:16
     * @param user
     * @return
    */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户", notes = "填写用户名、密码、电话等信息（提交表单）")
    public int addUser(@ApiParam(value = "User实体类") User user){
        return userService.addUser(user);
    }

    /**
     * @author leolee
     * @date 2018-08-07 21:17
     * @param pageNum
     * @param pageSize
     * @return
    */
//    @RequestMapping(value = "/all/{pageNum}/{pageSize}", method = RequestMethod.GET)
//    @ApiOperation(value = "查询所有用户", notes = "带分页：填写分页页码及分页大小")
//    public Object findAllUser(@PathVariable("pageNum") @ApiParam("分页页码") int pageNum,
//                              @PathVariable("pageSize") @ApiParam("分页大小") int pageSize){
//        return userService.findAllUser(pageNum, pageSize);
//    }

    /**
     * @author leolee
     * @date 2018-08-07 21:18
     * @param userName
     * @return
    */
//    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
//    @ApiOperation(value = "查询用户", notes = "通过用户名查询用户")
//    public User selectByUserName(@PathVariable("userName") @ApiParam(value = "用户名") String userName){
//        return userService.selectByUserName(userName);
//    }

    /**
     * @author leolee
     * @date 2018-08-07 21:18
     * @param userName
     * @return
    */
//    @RequestMapping(value = "/phone/{userName}", method = RequestMethod.GET)
//    @ApiOperation(value = "查询用户电话", notes = "通过用户名查询用户电话")
//    public String selectPhoneByUserName(@PathVariable("userName") @ApiParam(value = "用户名") String userName){
//        return userService.selectPhoneByUserName(userName);
//    }
}
