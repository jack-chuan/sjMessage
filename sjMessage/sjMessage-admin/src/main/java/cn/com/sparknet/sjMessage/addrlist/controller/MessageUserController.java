package cn.com.sparknet.sjMessage.addrlist.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sparknet.sjMessage.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.com.sparknet.sjMessage.addrlist.entity.MessageUserEntity;
import cn.com.sparknet.sjMessage.addrlist.service.MessageUserService;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.common.util.MapResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



/**
 * MSG用户表
 *
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@RestController
@RequestMapping(value = "addrlist/messageuser", produces = "application/json; charset=UTF-8")
@Api(value = "sjMessage Generator 自动生成接口", tags = "sjMsg-Gen：messageuser")
public class MessageUserController {
    @Autowired
    private MessageUserService messageUserService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("addrlist:messageuser:list")
    @ApiOperation(value = "messageuser列表", notes = "messageuser:list")
    public MapResult list(@RequestParam Map<String, Object> params){
        PageUtils page = messageUserService.queryPage(params);

        return MapResult.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("addrlist:messageuser:info")
    @ApiOperation(value = "messageuser信息", notes = "messageuser:info")
    public MapResult info(@PathVariable("userId") @ApiParam("userId") String userId){
        MessageUserEntity messageUser = messageUserService.getById(userId);

        return MapResult.success().put("messageUser", messageUser);
    }

    /**
    * @Description: 用户简要信息
    */
    @GetMapping("/defineInfo/{userId}")
    @RequiresPermissions("addrlist:messageuser:defineInfo")
    @ApiOperation(value = "messageuser信息", notes = "messageuser:defineInfo")
    public MapResult defineInfo(@PathVariable("userId") @ApiParam("userId") String userId){
        List<HashMap> userInfo = messageUserService.selectUserInfoByUserId(userId);

        return MapResult.success().put("userInfo", userInfo);
    }

    /**
     * @Description: 用户详细信息
     */
    @GetMapping("/detailInfo/{userId}")
    @RequiresPermissions("addrlist:messageuser:detailInfo")
    @ApiOperation(value = "messageuser信息", notes = "messageuser:detailInfo")
    public MapResult detailInfo(@PathVariable("userId") @ApiParam("userId") String userId){
        List<HashMap> userDetailInfo = messageUserService.selectUserDetailByUserId(userId);

        return MapResult.success().put("userDetailInfo", userDetailInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("addrlist:messageuser:save")
    @ApiOperation(value = "messageuser保存", notes = "messageuser:save")
    public MapResult save(@RequestBody MessageUserEntity messageUser){
        messageUserService.save(messageUser);

        return MapResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("addrlist:messageuser:update")
    @ApiOperation(value = "messageuser修改", notes = "messageuser:update")
    public MapResult update(@RequestBody MessageUserEntity messageUser){
        ValidatorUtils.validateEntity(messageUser);
        messageUserService.updateById(messageUser);
        
        return MapResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("addrlist:messageuser:delete")
    @ApiOperation(value = "messageuser删除", notes = "messageuser:delete")
    public MapResult delete(@RequestBody String[] userIds){
        messageUserService.removeByIds(Arrays.asList(userIds));

        return MapResult.success();
    }

}
