package cn.com.sparknet.sjMessage.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.service.ReceiveMsgService;
import cn.com.sparknet.sjMessage.common.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/receiveMsg", produces = "application/json; charset=UTF-8")
@Api(value = "mybatis 实体类映射及相关信息查询", tags = "收件箱操作接口")
public class ReceiveMsgController {

	private static final Logger logger = LoggerFactory.getLogger(ReceiveMsgController.class);
	
	@Autowired
    private ReceiveMsgService receiveMsgService;
	
	
	/*
	 * 收件箱已读未读列表
	 */
    @RequestMapping(value = "/queryInBoxList", method = RequestMethod.POST)
    @ApiOperation(value = "查询收件箱", notes = "带分页：填写分页页码及分页大小")
    public JsonResult queryInBoxList(@RequestParam("page") @ApiParam("分页页码") int pageNum,
                                    @RequestParam("limit") @ApiParam("分页大小") int pageSize,
                                    @RequestParam("state") @ApiParam("消息状态 A有效,R垃圾箱,D删除") String state,
                                    @RequestParam("receiver") @ApiParam("收件人") String receiver,
                                    @RequestParam("ifRead") @ApiParam("是否已读（1已读,0未读）") String ifRead,
                                    @RequestParam(value = "msgTitle", required = false) @ApiParam("消息标题") String msgTitle,
                                    @RequestParam(value = "receiveName", required = false) @ApiParam("收件人姓名") String receiveName,
                                    @RequestParam(value = "sendName", required = false) @ApiParam("发送人姓名") String sendName,
                                    HttpServletRequest request){
      List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
      Map<String, Object> map = new HashMap<String, Object>();
      JsonResult jsonResult;
      int count = 0;
      try {
    	    list = receiveMsgService.queryInBoxList(state,receiver,ifRead,pageNum, pageSize,msgTitle,receiveName,sendName);
    	    map = receiveMsgService.queryInBoxListCount(state, receiver, ifRead, pageNum, pageSize, msgTitle, receiveName, sendName);
		    count = Integer.parseInt(map.get("COUNTS").toString());
    	    jsonResult = new JsonResult(list,"200","查询成功",count);
		}catch(Exception e) {
			jsonResult = new JsonResult(list,"500","查询失败",count);
		}
      return jsonResult;
    }
    
    /*
	 * 标记为已读未读
	 */
    @RequestMapping(value = "/signRead/{id}/{ifRead}", method = RequestMethod.POST)
    @ApiOperation(value = "收件箱标记已读未读", notes = "入参消息发送表id集合以,隔开")
    public JsonResult signRead(@PathVariable("id") @ApiParam("消息发送表id集合，以,隔开") String id,
    						   @PathVariable("ifRead") @ApiParam("已读、未读（1已读,0未读）") String ifRead){
		int count = 0;
      JsonResult jsonResult;
      String result = "false";
      try {
    	    count = receiveMsgService.signRead(id,ifRead);
    	    if(count > 0) {
    	    	result = "true";
    	    }else {
    	    	result = "false";
    	    }
			jsonResult = new JsonResult(result,"200","标记成功");
		}catch(Exception e) {
			jsonResult = new JsonResult(result,"500","标记失败");
		}
      return jsonResult;
    }
    
    /*
	 * 收件箱消息删除
	 */
    @Transactional
    @RequestMapping(value = "/delInBoxMsg/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "收件箱消息删除", notes = "入参消息发送表id集合以,隔开")
    public JsonResult delInBoxMsg(@PathVariable("id") @ApiParam("消息发送表id集合，以,隔开") String id){
      int count = 0;
      JsonResult jsonResult;
      String result = "false";
      try {
    	    count = receiveMsgService.delInBoxMsg(id);
    	    if(count > 0) {
    	    	result = "true";
    	    }else {
    	    	result = "false";
    	    }
			jsonResult = new JsonResult(result,"200","删除成功");
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult(result,"500","删除失败");
		}
      return jsonResult;
    }
    
    /*
	 * 获取消息详情
	 */
    @RequestMapping(value = "/getMsgDetail/{msgId}", method = RequestMethod.POST)
    @ApiOperation(value = "获取消息详情", notes = "消息详情")
    public JsonResult getMsgDetail(@PathVariable("msgId") @ApiParam("消息信息表主键msgId") String msgId){
      int count = 0;
      JsonResult jsonResult;
      Map<String,Object> result = null;
      try {
    	    result = receiveMsgService.getMsgDetail(msgId);
		    jsonResult = new JsonResult(result,"200","查询成功");
		}catch(Exception e) {
			jsonResult = new JsonResult(result,"500","查询失败");
		}
      return jsonResult;
    }
    
    /*
	 * 回复、转发消息
	 */
    @Transactional
	@RequestMapping(value = "/replyOrForwardMsg/{sendType}", method = RequestMethod.POST)
    @ApiOperation(value = "回复、转发消息", notes = "消息（提交表单）")
	public JsonResult replyOrForwardMsg(@ApiParam(value = "Message实体字符串") String messageWithBLOBsStr,
			@ApiParam("附件ID集合") String fileIds,
			@PathVariable("sendType") @ApiParam("消息状态：T临时保存；S发送") String sendType,
                                        HttpServletRequest request) {
        JSONObject jsonObject = JSONObject.fromObject(messageWithBLOBsStr);
        MessageWithBLOBs messageWithBLOBs = (MessageWithBLOBs)JSONObject.toBean(jsonObject, MessageWithBLOBs.class);
    	messageWithBLOBs.setCreateTime(new Date());
    	messageWithBLOBs.setMsgId(UUID.randomUUID().toString());
    	JsonResult jsonResult;
		int count = 0;
		try{
			RUser rUser = (RUser)request.getSession().getAttribute("rUser");
            messageWithBLOBs.setSender(rUser.getUserId());
            messageWithBLOBs.setDeptId(rUser.getDeptId());
            messageWithBLOBs.setOrgId(rUser.getOrgId());
			count = receiveMsgService.replyMsg(messageWithBLOBs,fileIds,sendType);
			if(count > 0) {
				jsonResult = new JsonResult("200","成功");
			}else {
				jsonResult = new JsonResult("500", "失败");
			}
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult("500", "失败");
		}
		return jsonResult;
	}
}
