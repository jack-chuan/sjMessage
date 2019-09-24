package cn.com.sparknet.sjMessage.addrlist.controller;

import java.util.*;

import cn.com.sparknet.sjMessage.common.util.ZTreeResult;
import cn.com.sparknet.sjMessage.common.validator.ValidatorUtils;
import com.github.pagehelper.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.com.sparknet.sjMessage.addrlist.entity.MessageOrgEntity;
import cn.com.sparknet.sjMessage.addrlist.service.MessageOrgService;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.common.util.MapResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



/**
 * MSG机构表
 *
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@RestController
@RequestMapping(value = "addrlist/messageorg", produces = "application/json; charset=UTF-8")
@Api(value = "sjMessage Generator 自动生成接口", tags = "sjMsg-Gen：messageorg")
public class MessageOrgController {
    @Autowired
    private MessageOrgService messageorgService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("addrlist:messageorg:list")
    @ApiOperation(value = "messageorg列表", notes = "messageorg:list")
    public MapResult list(@RequestParam Map<String, Object> params){
        PageUtils page = messageorgService.queryPage(params);

        return MapResult.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{orgId}")
    @RequiresPermissions("addrlist:messageorg:info")
    @ApiOperation(value = "messageorg信息", notes = "messageorg:info")
    public MapResult info(@PathVariable("orgId") @ApiParam("orgId") String orgId){
        MessageOrgEntity messageorg = messageorgService.getById(orgId);

        return MapResult.success().put("messageorg", messageorg);
    }

    /**
     * @Description: 机构简要信息
     */
    @GetMapping("/defineInfo/{orgId}")
    @RequiresPermissions("addrlist:messageorg:defineInfo")
    @ApiOperation(value = "messageorg简要信息", notes = "messageorg:defineInfo")
    public MapResult defineInfo(@PathVariable("orgId") @ApiParam("orgId") String orgId){
        List<HashMap> orgInfo = messageorgService.selectOrgInfoByOrgId(orgId);

        return MapResult.success().put("orgInfo", orgInfo);
    }

    /**
     * @Description: 机构级联关系数据, zTree 异步加载
     */
    @RequestMapping(value = "/relation", method = {RequestMethod.POST,RequestMethod.GET})
    @RequiresPermissions("addrlist:messageorg:relation")
    @ApiOperation(value = "messageorg级联关系数据", notes = "messageorg:relation")
    public List<ZTreeResult> relation(
            @ApiParam(name = "parentOrgId", value = "parentOrgId")
            @RequestParam(name = "parentOrgId", required=false, defaultValue="000000000000000000000000000000000876") String parentOrgId){
         List<ZTreeResult> list = messageorgService.selectOrgChildrenByOrgParentId(parentOrgId);

        return list;
    }

    /**
     * @Description: 机构级联关系数据 All, zTree 一次性加载
     */
    @RequestMapping(value = "/relation/all", method = {RequestMethod.POST,RequestMethod.GET})
    @RequiresPermissions("addrlist:messageorg:relation")
    @ApiOperation(value = "messageorg级联关系数据 All", notes = "messageorg:relationAll")
    public List<ZTreeResult> relationAll(){
         List<ZTreeResult> list = messageorgService.selectAllOrgChildrenByOrgParentId();

        return list;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("addrlist:messageorg:save")
    @ApiOperation(value = "messageorg保存", notes = "messageorg:save")
    public MapResult save(@RequestBody MessageOrgEntity messageorg){
        messageorgService.save(messageorg);

        return MapResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("addrlist:messageorg:update")
    @ApiOperation(value = "messageorg修改", notes = "messageorg:update")
    public MapResult update(@RequestBody MessageOrgEntity messageorg){
        ValidatorUtils.validateEntity(messageorg);
        messageorgService.updateById(messageorg);
        
        return MapResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("addrlist:messageorg:delete")
    @ApiOperation(value = "messageorg删除", notes = "messageorg:delete")
    public MapResult delete(@RequestBody String[] orgIds){
        messageorgService.removeByIds(Arrays.asList(orgIds));

        return MapResult.success();
    }

}
