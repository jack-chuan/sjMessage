package cn.com.sparknet.sjMessage.addrlist.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sparknet.sjMessage.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.com.sparknet.sjMessage.addrlist.entity.MessageDeptEntity;
import cn.com.sparknet.sjMessage.addrlist.service.MessageDeptService;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.common.util.MapResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



/**
 * MSG部门表
 *
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@RestController
@RequestMapping(value = "addrlist/messagedept", produces = "application/json; charset=UTF-8")
@Api(value = "sjMessage Generator 自动生成接口", tags = "sjMsg-Gen：messagedept")
public class MessageDeptController {
    @Autowired
    private MessageDeptService messageDeptService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("addrlist:messagedept:list")
    @ApiOperation(value = "messagedept列表", notes = "messagedept:list")
    public MapResult list(@RequestParam Map<String, Object> params){
        PageUtils page = messageDeptService.queryPage(params);

        return MapResult.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{deptId}")
    @RequiresPermissions("addrlist:messagedept:info")
    @ApiOperation(value = "messagedept信息", notes = "messagedept:info")
    public MapResult info(@PathVariable("deptId") @ApiParam("deptId") String deptId){
        MessageDeptEntity messageDept = messageDeptService.getById(deptId);

        return MapResult.success().put("messageDept", messageDept);
    }

    /**
     * @Description: 部门简要信息
     */
    @GetMapping("/defineInfo")
    @RequiresPermissions("addrlist:messageuser:defineInfo")
    @ApiOperation(value = "messageuser信息", notes = "messageuser:defineInfo")
    public MapResult defineInfo(@RequestParam(value = "deptId", required = false) @ApiParam("deptId") String deptId){
        List<HashMap> deptInfo = messageDeptService.selectDeptInfoByDeptId(deptId);

        return MapResult.success().put("deptInfo", deptInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("addrlist:messagedept:save")
    @ApiOperation(value = "messagedept保存", notes = "messagedept:save")
    public MapResult save(@RequestBody MessageDeptEntity messageDept){
        messageDeptService.save(messageDept);

        return MapResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("addrlist:messagedept:update")
    @ApiOperation(value = "messagedept修改", notes = "messagedept:update")
    public MapResult update(@RequestBody MessageDeptEntity messageDept){
        ValidatorUtils.validateEntity(messageDept);
        messageDeptService.updateById(messageDept);
        
        return MapResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("addrlist:messagedept:delete")
    @ApiOperation(value = "messagedept删除", notes = "messagedept:delete")
    public MapResult delete(@RequestBody String[] deptIds){
        messageDeptService.removeByIds(Arrays.asList(deptIds));

        return MapResult.success();
    }

}
