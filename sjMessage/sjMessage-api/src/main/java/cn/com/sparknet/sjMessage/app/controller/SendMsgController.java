package cn.com.sparknet.sjMessage.app.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.entity.ReceiveMsg;
import cn.com.sparknet.sjMessage.app.entity.SendMsg;
import cn.com.sparknet.sjMessage.app.service.SendMsgService;
import cn.com.sparknet.sjMessage.common.util.JsonResult;
import cn.com.sparknet.sjMessage.common.util.WaterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/sendMsg", produces = "application/json; charset=UTF-8")
@Api(value = "mybatis 实体类映射及相关信息查询", tags = "发件箱操作接口")
public class SendMsgController {

	@Autowired
	SendMsgService sendMsgService;
	
	private static final Logger logger = LoggerFactory.getLogger(SendMsgController.class);
	
	
	  @Transactional
	  @PostMapping("/saveMsg")
	  @ApiOperation(value="保存/发送消息") 
	  public JsonResult saveMsg(@RequestParam("messageWithBLOBs") @ApiParam("消息主表") MessageWithBLOBs
	  messageWithBLOBs,HttpServletRequest request) { 
		  JsonResult jsonResult = null;
		  try { 
			  String state = WaterUtil.NVLToStr(request.getParameter("state"), "");
			  System.out.println("state="+state);
			  String msgId = WaterUtil.NVLToStr(request.getParameter("msgId"), "");
			  System.out.println("msgId="+msgId);
			  if("".equals(msgId)) {//新消息
				  if("T".equals(state)) {//保存消息
					  messageWithBLOBs.setMsgId(UUID.randomUUID().toString());
					  messageWithBLOBs.setSender("ef067723-383d-4192-8679-23f58a266f39");
					  messageWithBLOBs.setDeptId("6be2e71c-1253-405b-a84e-ab62e4973e5d");
					  messageWithBLOBs.setOrgId("000000000000000000000000000000000876");
					  messageWithBLOBs.setCreateTime(new Date());
					  messageWithBLOBs.setSendTime(new Date());
					  messageWithBLOBs.setState("A");
					  messageWithBLOBs.setReceiveName("杜云川");
					  sendMsgService.saveMsg(messageWithBLOBs);
				  }else if("S".equals(state)){//发送消息
					  
				  }
			  }else {
				  if("T".equals(state)) {//保存消息
					  
				  }else if("S".equals(state)) {//发送消息
					  
				  }
			  }
			  
		  }catch (Exception e) {
			  
		  }
		  return jsonResult;
	  }
	 
	
	@PostMapping(value="/querySendMsgList")
	@ApiOperation(value="查询发件箱列表",notes="带分页")
	public JsonResult querySendMsgList(@RequestParam("pageNum") @ApiParam("分页页码") int pageNum,
			@RequestParam("pageSize") @ApiParam("分页大小") int pageSize,
			@RequestParam("state") @ApiParam("状态") String state,
			@RequestParam(value = "msgTitle", required = false) @ApiParam("消息标题") String msgTitle,
            @RequestParam(value = "receiveName", required = false) @ApiParam("收件人姓名") String receiveName,
            @RequestParam(value = "sendName", required = false) @ApiParam("发送人姓名") String sendName,
			@RequestParam(value = "sDate", required = false) @ApiParam("开始时间") String sDate,
			@RequestParam(value = "eDate", required = false) @ApiParam("结束时间") String eDate,
			                            HttpServletRequest request) {
		
		JsonResult jsonResult = null;
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		int count = 0;
		try {
			RUser rUser = (RUser)request.getSession().getAttribute("rUser");
			String sender = rUser.getUserId();
			map = sendMsgService.querySendMsgListCount(pageNum, pageSize, sender, state, msgTitle, receiveName, sendName, sDate, eDate);
			resultList = sendMsgService.querySendMsgList(pageNum, pageSize, sender, state, msgTitle, receiveName, sendName, sDate, eDate);
			count = Integer.parseInt(map.get("COUNTS").toString());
			jsonResult = new JsonResult(resultList, "200", "查询成功",count);
		}catch (Exception e) {
			jsonResult = new JsonResult(resultList, "500", "查询失败",count);
		}
		return jsonResult;
	}
	
	@PostMapping(value="/selectByPrimaryKey")
	@ApiOperation(value="查询发件箱消息详情")
	public JsonResult selectByPrimaryKey(@RequestParam("msgId") @ApiParam("消息ID") String msgId) {
		JsonResult jsonResult = null;
		try {
			MessageWithBLOBs message = sendMsgService.selectByPrimaryKey(msgId);
			jsonResult = new JsonResult(message, "200", "查询发件箱消息详情成功！");
		}catch (Exception e) {
			jsonResult = new JsonResult("500","查询发件箱消息详情失败！");
		}
		return jsonResult;
	}
	 
	@Transactional
	@PostMapping(value="/deleteSendMsg")
	@ApiOperation(value="删除发件箱消息")
	public JsonResult deleteSendMsg(@RequestParam("state") @ApiParam("消息状态") String state,
			@RequestParam("id") @ApiParam("消息发送表Id集合，以，隔开") String id) {
		JsonResult jsonResult = null;
		int count = 0;
		try {
			count = sendMsgService.deleteSendMsg(state, id);
			if(count > 0) {
				jsonResult = new JsonResult("200","删除成功！");
			}else {
				jsonResult = new JsonResult("500","删除失败！");
			}
			
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult("500","删除失败！");
		}
		return jsonResult;
	}

	@Transactional
	@PostMapping(value="/backMsg")
	@ApiOperation(value="撤回消息")
	public JsonResult backMsg(@RequestParam("msgId") @ApiParam("消息ID") String msgId) {
		JsonResult jsonResult = null;
		try {
			sendMsgService.backMsg(msgId);
			jsonResult = new JsonResult("200","撤回成功！");
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult("500","撤回失败！");
		}
		return jsonResult;
	}
}