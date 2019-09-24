package cn.com.sparknet.sjMessage.mq.controller;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.sparknet.sjMessage.common.util.JsonResult;
import cn.com.sparknet.sjMessage.mq.service.SendMsgForRabbitmqService;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rabbitmq")
public class SendMsgForRabbitmqController {
	
	@Autowired
	SendMsgForRabbitmqService sendMsgForRabbitmqService;

	@PostMapping("/sendMsg")
	@ApiOperation(value = "发送、回复、转发消息", notes = "消息（提交表单）")
	public JsonResult sendMsg(@RequestParam(value="messageWithBLOBsStr") String messageWithBLOBsStr,
			@RequestParam(value="sendType") String sendType,
			@RequestParam(value="fileIds") String fileIds) {
		JsonResult jsonResult = null;
		int count = 0;
		JSONObject jsonObject = JSONObject.fromObject(messageWithBLOBsStr);
        try {
        	count = sendMsgForRabbitmqService.sendMsg(jsonObject, sendType, fileIds);
        	if(count > 0) {
				jsonResult = new JsonResult("200", "成功");
			}else {
				jsonResult = new JsonResult("500", "失败");
			}
        }catch (Exception e) {
        	jsonResult = new JsonResult("500", "失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	/**
	 * 发件箱发送消息
	 * @param messageWithBLOBsStr
	 * @param sendType
	 * @param fileIds
	 * @return
	 */
	@PostMapping("/sendMessage")
	@ApiOperation(value = "发送、回复、转发消息", notes = "消息（提交表单）")
	public JsonResult sendMessage(@RequestParam(value="messageWithBLOBsStr") String messageWithBLOBsStr,
			@RequestParam(value="sendType") String sendType,
			@RequestParam(value="fileIds") String fileIds,
                                  HttpServletRequest request) {
		JsonResult jsonResult = null;
		int count = 0;
		JSONObject jsonObject = JSONObject.fromObject(messageWithBLOBsStr);
        try {
            RUser rUser = (RUser)request.getSession().getAttribute("rUser");
            jsonObject.put("sender",rUser.getUserId());
            jsonObject.put("deptId",rUser.getDeptId());
            jsonObject.put("orgId",rUser.getOrgId());
        	count = sendMsgForRabbitmqService.sendMessage(jsonObject, sendType, fileIds);
        	if(count > 0) {
				jsonResult = new JsonResult("200", "成功");
			}else {
				jsonResult = new JsonResult("500", "失败");
			}
        }catch (Exception e) {
        	jsonResult = new JsonResult("500", "失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	/**
	 * 草稿箱发送消息
	 * @param messageWithBLOBsStr
	 * @param sendType
	 * @param fileIds
	 * @return
	 */
	@PostMapping("/draftSendMessage")
	@ApiOperation(value = "发送、回复、转发消息", notes = "消息（提交表单）")
	public JsonResult draftSendMessage(@RequestParam(value="messageWithBLOBsStr") String messageWithBLOBsStr,
			@RequestParam(value="sendType") String sendType,
			@RequestParam(value="fileIds") String fileIds,
                                  HttpServletRequest request) {
		JsonResult jsonResult = null;
		int count = 0;
		JSONObject jsonObject = JSONObject.fromObject(messageWithBLOBsStr);
        try {
            RUser rUser = (RUser)request.getSession().getAttribute("rUser");
            jsonObject.put("sender",rUser.getUserId());
            jsonObject.put("deptId",rUser.getDeptId());
            jsonObject.put("orgId",rUser.getOrgId());
        	count = sendMsgForRabbitmqService.draftSendMessage(jsonObject, sendType, fileIds);
        	if(count > 0) {
				jsonResult = new JsonResult("200", "成功");
			}else {
				jsonResult = new JsonResult("500", "失败");
			}
        }catch (Exception e) {
        	jsonResult = new JsonResult("500", "失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
}
