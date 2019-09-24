package cn.com.sparknet.sjMessage.mq.service;

import net.sf.json.JSONObject;

public interface SendMsgForRabbitmqService {

	int sendMsg(JSONObject jsonObject,String sendType,String fileIds);
	
	int sendMessage(JSONObject jsonObject,String sendType,String fileIds);
	
	int draftSendMessage(JSONObject jsonObject,String sendType,String fileIds);
	
}
