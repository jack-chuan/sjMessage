package cn.com.sparknet.sjMessage.addrlist.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: MessageAddrlistTreeController
 * @description: addrlist tree
 * @author: Leo Lee
 * @create: 2019-03-29 10:58
 **/
@Controller
@RequestMapping(value = "addrlist/addrlistTree")
@Api(value = "sjMessage Leo 手写接口", tags = "sjMsg-Leo：addrlistTree")
public class MessageAddrlistTreeController {
    /**
     * @Description: 部门树
     */
//    @GetMapping("/deptTree/{deptId}")
    @GetMapping("/deptTree/index")
    @RequiresPermissions("addrlist:addrlistTree:deptTree")
    @ApiOperation(value = "addrlist部门树", notes = "addrlistTree:deptTree")
//    public MapResult deptTree(@PathVariable("deptId") @ApiParam("deptId") String deptId){
    public String  deptTree(){
//        List<HashMap> deptInfo = messageDeptService.selectDeptInfoByDeptId(deptId);

//        return MapResult.success().put("deptInfo", deptInfo);
        return "addrlist/addrlist.html";
    }

}
