package cn.com.sparknet.sjMessage.mq.service.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.entity.SendMsg;
import cn.com.sparknet.sjMessage.app.mapper.MessageMapper;
import cn.com.sparknet.sjMessage.app.mapper.MsgFileMapper;
import cn.com.sparknet.sjMessage.app.mapper.SendMsgMapper;
import cn.com.sparknet.sjMessage.mq.client.MessageProducer;
import cn.com.sparknet.sjMessage.mq.service.SendMsgForRabbitmqService;
import net.sf.json.JSONObject;

@Service
public class SendMsgForRabbitmqServiceImpl implements SendMsgForRabbitmqService {
	
	private static final Logger logger = LoggerFactory.getLogger(SendMsgForRabbitmqServiceImpl.class);

	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private MsgFileMapper msgFileMapper;
	
	@Autowired
	private SendMsgMapper sendMsgMapper;
	
	@Autowired
	MessageProducer producer;
	
	/**
	 * 单个消费者
	 */
	@Override
	@Transactional
	public int sendMsg(JSONObject jsonObject,String sendType,String fileIds) {
		MessageWithBLOBs messageWithBLOBs = (MessageWithBLOBs)JSONObject.toBean(jsonObject, MessageWithBLOBs.class);
        messageWithBLOBs.setCreateTime(new Date());
        messageWithBLOBs.setMsgId(UUID.randomUUID().toString());
        SendMsg sendMsg = new SendMsg();
        int count = 0;
        try {
        	if(!"".equals(sendType)) {
    			if("S".equals(sendType)) {
    				messageWithBLOBs.setSendTime(new Date());
    				sendMsg.setType("S");
    			} else {
    				sendMsg.setType("T");
                }
    			//默认为保存
    			sendMsg.setId(UUID.randomUUID().toString());
    			sendMsg.setMsgId(messageWithBLOBs.getMsgId());
    			sendMsg.setSender(messageWithBLOBs.getSender());
    			sendMsg.setCreateDate(new Date());
    			sendMsg.setState(sendType);
    			//保存消息表
    			count = count + messageMapper.insert(messageWithBLOBs);
    			//保存发送表
    			count = count + sendMsgMapper.insert(sendMsg);
    			//修改附件表msgId
    			if(!"".equals(fileIds)) {
    				String fileId[] = fileIds.split(",");
    				for(int i=0;i<fileId.length;i++) {
    					count = count + msgFileMapper.updateFileByMsgId(messageWithBLOBs.getMsgId(),fileId[i]);
    				}
    			}
    			//当sendType为发送时，再插入接收表
    			if("S".equals(sendType)) {
    				producer.sendMsg(messageWithBLOBs);
    			}
    		}
        }catch (JsonProcessingException e) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	logger.error("生产端出现异常", e);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error(e.getMessage());
		}
        return count;
	}

	/**
	 * 多个消费者，处理高并发（消息系统用此方法）
	 */
	@Override
	@Transactional
	public int sendMessage(JSONObject jsonObject,String sendType,String fileIds) {
		MessageWithBLOBs messageWithBLOBs = (MessageWithBLOBs)JSONObject.toBean(jsonObject, MessageWithBLOBs.class);
        messageWithBLOBs.setCreateTime(new Date());
        messageWithBLOBs.setMsgId(UUID.randomUUID().toString());
        SendMsg sendMsg = new SendMsg();
        int count = 0;
        try {
        	if(!"".equals(sendType)) {
    			if("S".equals(sendType)) {
    				messageWithBLOBs.setSendTime(new Date());
    				sendMsg.setType("S");
    			} else {
    				sendMsg.setType("T");
                }
    			//默认为保存
    			sendMsg.setId(UUID.randomUUID().toString());
    			sendMsg.setMsgId(messageWithBLOBs.getMsgId());
    			sendMsg.setSender(messageWithBLOBs.getSender());
    			sendMsg.setCreateDate(messageWithBLOBs.getCreateTime());
    			sendMsg.setState(sendType);
    			//保存消息表
    			count = count + messageMapper.insert(messageWithBLOBs);
    			//保存发送表
    			count = count + sendMsgMapper.insert(sendMsg);
    			//修改附件表msgId
    			if(!"".equals(fileIds)) {
    				String fileId[] = fileIds.split(",");
    				for(int i=0;i<fileId.length;i++) {
    					count = count + msgFileMapper.updateFileByMsgId(messageWithBLOBs.getMsgId(),fileId[i]);
    				}
    			}
    			//当sendType为发送时，再插入接收表
    			//此时使用rabbitmq
    			if("S".equals(sendType)) {
    				producer.sendMessage(messageWithBLOBs);
    			}
    		}
        }catch (JsonProcessingException e) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	logger.error("生产端出现异常", e);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error(e.getMessage());
		}
        return count;
	}

	@Transactional
	@Override
	public int draftSendMessage(JSONObject jsonObject, String sendType, String fileIds) {
		MessageWithBLOBs messageWithBLOBs = (MessageWithBLOBs)JSONObject.toBean(jsonObject, MessageWithBLOBs.class);
        messageWithBLOBs.setCreateTime(new Date());
        SendMsg sendMsg = sendMsgMapper.selectByMsgId(messageWithBLOBs.getMsgId());
        int count = 0;
        try {
        	if(!"".equals(sendType)) {
        		if("S".equals(sendType)) {
    				messageWithBLOBs.setSendTime(new Date());
    				sendMsg.setType("S");
    			} else {
    				sendMsg.setType("T");
                }
        		//默认为保存
        		sendMsg.setState(sendType);
        		sendMsg.setCreateDate(new Date());
        		//保存消息表
    			count = count + messageMapper.updateByPrimaryKey(messageWithBLOBs);
    			//保存发送表
    			count = count + sendMsgMapper.updateByPrimaryKey(sendMsg);
    			//修改附件表msgId
    			if(!"".equals(fileIds)) {
    				String fileId[] = fileIds.split(",");
    				for(int i=0;i<fileId.length;i++) {
    					count = count + msgFileMapper.updateFileByMsgId(messageWithBLOBs.getMsgId(),fileId[i]);
    				}
    			}
    			//当sendType为发送时，再插入接收表
    			//此时使用rabbitmq
    			if("S".equals(sendType)) {
    				producer.draftSendMessage(messageWithBLOBs);
    			}
    		}
        }catch (JsonProcessingException e) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	logger.error("生产端出现异常", e);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error(e.getMessage());
		}
        return count;
	}

}
