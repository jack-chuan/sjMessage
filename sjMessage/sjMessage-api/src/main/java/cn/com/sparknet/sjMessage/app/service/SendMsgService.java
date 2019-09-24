package cn.com.sparknet.sjMessage.app.service;

import java.util.List;
import java.util.Map;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.entity.SendMsg;

public interface SendMsgService {

	/**
	 * 保存/发送消息
	 * @param message消息主表
	 * @param sendMsg消息发送表
	 */
	void saveMsg(MessageWithBLOBs message);
	
	/**
	 * 查询发件箱列表
	 * @param pageNum
	 * @param pageSize
	 * @param sender
	 * @param state
	 * @author duyc
	 * @return
	 */
	List<Map<String, Object>> querySendMsgList(int pageNum, int pageSize, String sender, String state, String msgTitle, String receiveName, String sendName, String sDate, String eDate);
	
	/**
	 * 查询发件箱消息详情
	 * @param msgId
	 * @author duyc
	 * @return
	 */
	MessageWithBLOBs selectByPrimaryKey(String msgId);
	
	/**
	 * 删除发件箱消息
	 * @param state "R"表示从发件箱>垃圾箱    "D"表示从垃圾箱删除
	 * @param msgId
	 */
	int deleteSendMsg(String state,String msgId);
	
	/**
	 * 撤回消息
	 * @param msgId
	 */
	void backMsg(String msgId);
	
	Map<String, Object> querySendMsgListCount(int pageNum, int pageSize, String sender, String state, String msgTitle, String receiveName, String sendName, String sDate, String eDate);
}
