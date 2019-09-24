package cn.com.sparknet.sjMessage.app.service;

import java.util.List;
import java.util.Map;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;

public interface ReceiveMsgService {

	/*
	 * 获取收件箱已读未读列表
	 */
	List<Map<String,Object>> queryInBoxList(String state,String receiver,String ifRead,int pageNum, int pageSize, String msgTitle, String receiveName, String sendName);
	
	/*
	 * 标记为已读未读
	 */
	int signRead(String id,String ifRead);
	
	/*
	 * 收件箱消息删除
	 */
	int delInBoxMsg(String id);
	
	/*
	 * 获取消息详情
	 */
	Map<String,Object> getMsgDetail(String msgId);
	
	/*
	 * 收件箱回复消息
	 */
	int replyMsg(MessageWithBLOBs messageWithBLOBs,String fileIds,String sendType);
	
	/*
	 * 获取收件箱已读未读列表条数
	 */
	Map<String,Object> queryInBoxListCount(String state,String receiver,String ifRead,int pageNum, int pageSize, String msgTitle, String receiveName, String sendName);
}
