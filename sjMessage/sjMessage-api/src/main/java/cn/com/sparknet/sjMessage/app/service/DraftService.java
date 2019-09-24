package cn.com.sparknet.sjMessage.app.service;

import java.util.List;
import java.util.Map;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;

public interface DraftService {

	/*
	 * 草稿箱消息列表
	 */
	public List<Map<String, Object>> queryDraftBoxList(String sender,int pageNum, int pageSize, String msgTitle, String receiveName, String sendName, String sDate, String eDate);
	
	/*
	 * 草稿箱消息删除
	 */
	int deleteDraftMsg(String id);
	
	/*
	 * 草稿箱消息保存、发送，根据sendType判断是否保存发送
	 */
	public int draftSendMsg(MessageWithBLOBs messageWithBLOBs, String fileIds,String sendType);
	
	public Map<String, Object> queryDraftBoxListCount(String sender,int pageNum, int pageSize, String msgTitle, String receiveName, String sendName, String sDate, String eDate);
}
