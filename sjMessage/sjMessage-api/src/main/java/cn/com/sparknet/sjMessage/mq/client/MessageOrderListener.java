package cn.com.sparknet.sjMessage.mq.client;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.entity.ReceiveMsg;
import cn.com.sparknet.sjMessage.app.mapper.ReceiveMsgMapper;

@Component("messageOrderListener")
public class MessageOrderListener implements ChannelAwareMessageListener{

	private static final Logger logger = LoggerFactory.getLogger(MessageOrderListener.class);
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private ReceiveMsgMapper receiveMsgMapper;
	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		
		long tag = message.getMessageProperties().getDeliveryTag();
		
		try {
			logger.info("-----消费端接收消息开始(高并发)-----");
			ReceiveMsg receive = new ReceiveMsg();
			byte[] body = message.getBody();
			MessageWithBLOBs messageWithBlobs = objectMapper.readValue(body, MessageWithBLOBs.class);
			String sendTo[] = messageWithBlobs.getReceiver().split(",");
			for(int j=0;j<sendTo.length;j++) {
				List<Map<String, Object>> userList = receiveMsgMapper.querySendUserList(sendTo[j]);
				for(int k=0;k<userList.size();k++) {
					Map<String, Object> user = userList.get(k);
					receive = new ReceiveMsg();
					receive.setId(UUID.randomUUID().toString());
					receive.setReceiver(user.get("USER_ID").toString());
					receive.setMsgId(messageWithBlobs.getMsgId());
					receive.setCreateDate(new Date());
					receive.setState("A");
					receive.setIfRead("0");
					receive.setType("R");
					receiveMsgMapper.insert(receive);
				}
			}
			channel.basicAck(tag , true); //是否批量处理.true:将一次性ack所有小于deliveryTag的消息
			logger.info("-----消费端接收消息结束(高并发)-----");
		}catch (Exception e) {
			logger.error("消费端发生异常", e);
			channel.basicReject(tag, false); //true 表示将该消息重新如队列，false 表示该消息将删除
		}
	}
}
