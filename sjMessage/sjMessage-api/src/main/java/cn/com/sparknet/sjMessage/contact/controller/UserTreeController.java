package cn.com.sparknet.sjMessage.contact.controller;

import cn.com.sparknet.sjMessage.common.util.ZTreeResult;
import cn.com.sparknet.sjMessage.contact.service.MessageUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program:
 * @description: userTree 人员树页面
 * @author: zhanghm
 * @create: 2019-05-13
 **/

@Controller
@RequestMapping(value = "userTree")
public class UserTreeController {

	@Autowired
	private MessageUserService messageUserService;

	/**
	 * 跳转到人员树
	 * UserTree
	 */
	@GetMapping("/index")
	public String  userTree(){
		return "contact/userTree.html";
	}


	/**
	 * 人员树
	 * userTree
	 */
	@ResponseBody
	@GetMapping(value = "/all")
	public List<ZTreeResult> userTreeAll(@RequestParam("orgId") @ApiParam("机构Id") String orgId){
		List<ZTreeResult> list = messageUserService.selectAllUserChildrenByDeptId(orgId);
		return list;
	}
}
