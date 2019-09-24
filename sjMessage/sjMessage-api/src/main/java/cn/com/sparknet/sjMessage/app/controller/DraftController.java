package cn.com.sparknet.sjMessage.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.service.DraftService;
import cn.com.sparknet.sjMessage.common.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/draftBox", produces = "application/json; charset=UTF-8")
@Api(value = "mybatis 实体类映射及相关信息查询", tags = "草稿箱操作接口")
public class DraftController {
	
	private static final Logger logger = LoggerFactory.getLogger(DraftController.class);
	
	@Autowired
    private DraftService draftService;
	
	/*
	 * 获取草稿箱消息列表
	 */
    @RequestMapping(value = "/queryDraftBoxList", method = RequestMethod.POST)
    @ApiOperation(value = "查询草稿箱", notes = "带分页：填写分页页码及分页大小")
    public JsonResult queryDraftBoxList(@RequestParam("pageNum") @ApiParam("分页页码") int pageNum,
    								@RequestParam("pageSize") @ApiParam("分页大小") int pageSize,
//    								@RequestParam("sender") @ApiParam("发送人") String sender,
    								@RequestParam(value = "msgTitle", required = false) @ApiParam("消息标题") String msgTitle,
    								@RequestParam(value = "receiveName", required = false) @ApiParam("收件人姓名") String receiveName,
    								@RequestParam(value = "sendName", required = false) @ApiParam("发送人姓名") String sendName,
									@RequestParam(value = "sDate", required = false) @ApiParam("开始时间") String sDate,
									@RequestParam(value = "eDate", required = false) @ApiParam("结束时间") String eDate,
                                    HttpServletRequest request){
      List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
      Map<String, Object> map = new HashMap<String, Object>();
      JsonResult jsonResult = null;
      int count = 0;
      try {
      		RUser rUser = (RUser)request.getSession().getAttribute("rUser");
		  	String sender = rUser.getUserId();
    	    list = draftService.queryDraftBoxList(sender,pageNum, pageSize,msgTitle,receiveName,sendName,sDate,eDate);
    	    map = draftService.queryDraftBoxListCount(sender, pageNum, pageSize, msgTitle, receiveName, sendName, sDate, eDate);
            count = Integer.parseInt(map.get("COUNTS").toString());
			jsonResult = new JsonResult(list,"200","查询成功",count);
		}catch(Exception e) {
			jsonResult = new JsonResult(list,"500","查询失败",count);
		}
      return jsonResult;
    }
    
    /*
	 * 草稿箱消息删除
	 */
    @Transactional
    @RequestMapping(value = "/deleteDraftMsg", method = RequestMethod.POST)
    @ApiOperation(value = "草稿箱消息删除", notes = "消息表主键msgId集合以,隔开")
    public JsonResult deleteDraftMsg(@RequestParam("id") @ApiParam("消息表主键msgId集合，以,隔开") String id){
      int count = 0;
      JsonResult jsonResult;
      String result = "false";
      try {
    	    count = draftService.deleteDraftMsg(id);
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
	 * 草稿箱消息保存、发送，根据sendType判断是否保存发送
	 */
    @Transactional
	@RequestMapping(value = "/draftSendMsg/{sendType}", method = RequestMethod.POST)
    @ApiOperation(value = "草稿箱消息保存、发送", notes = "消息（提交表单）")
	public JsonResult draftSendMsg(@ApiParam(value = "Message实体字符串") String messageWithBLOBsStr,
			@ApiParam("附件ID集合") String fileIds,
			@PathVariable("sendType") @ApiParam("消息状态：T临时保存；S发送") String sendType) {
        JSONObject jsonObject = JSONObject.fromObject(messageWithBLOBsStr);
        MessageWithBLOBs messageWithBLOBs = (MessageWithBLOBs)JSONObject.toBean(jsonObject, MessageWithBLOBs.class);
    	messageWithBLOBs.setCreateTime(new Date());
    	JsonResult jsonResult;
		int count = 0;
		try{
			count = draftService.draftSendMsg(messageWithBLOBs,fileIds,sendType);
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
