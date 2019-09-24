package cn.com.sparknet.sjMessage.contact.controller;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import cn.com.sparknet.sjMessage.common.util.JsonResult;

import cn.com.sparknet.sjMessage.common.util.ZTreeResult;
import cn.com.sparknet.sjMessage.contact.service.ContactService;
import cn.com.sparknet.sjMessage.contact.service.MessageDeptService;
import cn.com.sparknet.sjMessage.contact.service.MessageOrgService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: MessageAddrlistTreeController
 * @description: Contact 联系人页面
 * @author: zhanghm
 * @create: 2019-05-13
 **/
@Controller
@RequestMapping(value = "contact")
public class ContactController {

    @Autowired
    private MessageDeptService messageDeptService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private MessageOrgService messageorgService;

    /**
     * @Description: 联系人
     */
    @GetMapping("/index")
    @RequiresPermissions("contact:index")
      public String  deptTree(){
        return "contact/contact.html";
    }

    /**
     * 部门列表
     * 根据机构Id查询
     */

    @ResponseBody
    @PostMapping("/deptList" )
    public JsonResult deptList(@RequestParam("orgId") @ApiParam("机构Id") String orgId){
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        JsonResult jsonResult = null;
        try {
            resultList = messageDeptService.selectDeptInfoByOrgId(orgId);
            jsonResult = new JsonResult(resultList,"200","查询成功");
        }catch (Exception e){
            jsonResult = new JsonResult(resultList,"500","查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @PostMapping("/orgList" )
    public JsonResult orgList(){
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        JsonResult jsonResult = null;
        try {
            resultList = messageorgService.selectOrgInfo();
            jsonResult = new JsonResult(resultList,"200","查询成功");
        }catch (Exception e){
            jsonResult = new JsonResult(resultList,"500","查询失败");
        }
        return jsonResult;
    }


    /**
     * 人员列表
     * 根据部门Id查询
     * 分页
     */
    @ResponseBody
    @GetMapping("/personListByDeptId" )
    public JsonResult personListByDeptId(@RequestParam("deptId") @ApiParam("部门Id") String deptId,
                                 @RequestParam("pageNum") @ApiParam("当前页") String pageNum,
                                 @RequestParam("pageSize") @ApiParam("每页条数") String pageSize){
        JsonResult jsonResult;
        PageInfo pageInfo = contactService.selectPersonInfoByDeptId(deptId,Integer.parseInt(pageNum),Integer.parseInt(pageSize));

        try {
            jsonResult = new JsonResult(pageInfo,"200","查询成功");
        }catch (Exception e){
            jsonResult = new JsonResult(pageInfo,"500","查询失败");
        }
        return jsonResult;
    }

    /**
     * @Description: 机构级联关系数据 All, zTree 一次性加载
     */
    @ResponseBody
    @GetMapping(value = "/orgTree")
    public List<ZTreeResult> relationAll(HttpServletRequest request){
		List<ZTreeResult> list = messageorgService.selectAllOrgChildrenByOrgParentId();
        return list;
    }

    /**
     * 人员列表
     * 根据机构Id查询
     * 分页
     */
    @ResponseBody
    @PostMapping("/personListByOrgId" )
    public JsonResult personList(@RequestParam("page") @ApiParam("分页页码") int pageNum,
                                 @RequestParam("limit") @ApiParam("分页大小") int pageSize,
                                 @RequestParam(value = "orgId", required = false) @ApiParam("组织") String orgId,
                                 @RequestParam(value = "deptId", required = false) @ApiParam("部门") String deptId,
                                 @RequestParam(value = "personName", required = false) @ApiParam("姓名") String personName,
                                 @RequestParam(value = "personDuty", required = false) @ApiParam("职位") String personDuty,
                                 HttpServletRequest request){
        JsonResult jsonResult = null;
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        int count = 0;
        try {
            if(StringUtils.isBlank(orgId)){
                RUser rUser = (RUser)request.getSession().getAttribute("rUser");
                orgId = rUser.getOrgId();
            }
            resultList = contactService.selectPersonInfoByOrgId(pageNum, pageSize, orgId, deptId, personName, personDuty);
            map = contactService.selectPersonInfoByOrgIdCount(pageNum, pageSize, orgId, deptId, personName, personDuty);
            count = Integer.parseInt(map.get("COUNTS").toString());
            jsonResult = new JsonResult(resultList,"200","查询成功",count);
        }catch (Exception e){
            jsonResult = new JsonResult(resultList,"500","查询失败",count);
        }
        return jsonResult;
    }


    /**
     * 人员列表
     * 搜索
     * 分页
     */
    @ResponseBody
    @GetMapping("/personList/all" )
    public JsonResult selectAllPerson(@RequestParam("pageNum") @ApiParam("当前页") String pageNum,
                                      @RequestParam("pageSize") @ApiParam("每页条数") String pageSize,
                                      @RequestParam("personName") @ApiParam("姓名") String personName,
                                      @RequestParam("personDuty") @ApiParam("职位") String personDuty){
        JsonResult jsonResult;
        PageInfo PageInfo = contactService.selectAllPersonInfo(personName,personDuty,Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        try {
            jsonResult = new JsonResult(PageInfo,"200","查询成功");
        }catch (Exception e){
            jsonResult = new JsonResult(PageInfo,"500","查询失败");
        }
            return jsonResult;
    }



}
