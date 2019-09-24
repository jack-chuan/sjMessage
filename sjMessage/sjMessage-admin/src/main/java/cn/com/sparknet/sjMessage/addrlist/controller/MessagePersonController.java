package cn.com.sparknet.sjMessage.addrlist.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sparknet.sjMessage.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.com.sparknet.sjMessage.addrlist.entity.MessagePersonEntity;
import cn.com.sparknet.sjMessage.addrlist.service.MessagePersonService;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.common.util.MapResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



/**
 * MSG人员表
 *
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@RestController
@RequestMapping(value = "addrlist/messageperson", produces = "application/json; charset=UTF-8")
@Api(value = "sjMessage Generator 自动生成接口", tags = "sjMsg-Gen：messageperson")
public class MessagePersonController {
    @Autowired
    private MessagePersonService messagePersonService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("addrlist:messageperson:list")
    @ApiOperation(value = "messageperson列表", notes = "messageperson:list")
    public MapResult list(@RequestParam Map<String, Object> params){
        PageUtils page = messagePersonService.queryPage(params);

        return MapResult.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{personId}")
    @RequiresPermissions("addrlist:messageperson:info")
    @ApiOperation(value = "messageperson信息", notes = "messageperson:info")
    public MapResult info(@PathVariable("personId") @ApiParam("personId") String personId){
        MessagePersonEntity messagePerson = messagePersonService.getById(personId);

        return MapResult.success().put("messagePerson", messagePerson);
    }

    /**
     * @Description: 人员简要信息
     */
    @GetMapping("/defineInfo/{personId}")
    @RequiresPermissions("addrlist:messageuser:defineInfo")
    @ApiOperation(value = "messageuser信息", notes = "messageuser:defineInfo")
    public MapResult defineInfo(@PathVariable("personId") @ApiParam("personId") String personId){
        List<HashMap> personInfo = messagePersonService.selectPersonInfoByPersonId(personId);

        return MapResult.success().put("personInfo", personInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("addrlist:messageperson:save")
    @ApiOperation(value = "messageperson保存", notes = "messageperson:save")
    public MapResult save(@RequestBody MessagePersonEntity messagePerson){
        messagePersonService.save(messagePerson);

        return MapResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("addrlist:messageperson:update")
    @ApiOperation(value = "messageperson修改", notes = "messageperson:update")
    public MapResult update(@RequestBody MessagePersonEntity messagePerson){
        ValidatorUtils.validateEntity(messagePerson);
        messagePersonService.updateById(messagePerson);
        
        return MapResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("addrlist:messageperson:delete")
    @ApiOperation(value = "messageperson删除", notes = "messageperson:delete")
    public MapResult delete(@RequestBody String[] personIds){
        messagePersonService.removeByIds(Arrays.asList(personIds));

        return MapResult.success();
    }

}
