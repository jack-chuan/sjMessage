package cn.com.sparknet.sjMessage.mq.client;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.entity.ReceiveMsg;
import cn.com.sparknet.sjMessage.app.mapper.ReceiveMsgMapper;

@Component
public class MessageConsumer {

	private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private ReceiveMsgMapper receiveMsgMapper;
	
	//@RabbitListener(queues="${log.user.queue.name}",containerFactory="singleListenerContainer")
	public void receiveMsg(@Payload byte[] message) {
		try {
			logger.info("-----消费端接收消息开始-----");
			ReceiveMsg receive = new ReceiveMsg();
			MessageWithBLOBs messageWithBlobs = objectMapper.readValue(message, MessageWithBLOBs.class);
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
			logger.info("-----消费端接收消息结束-----");
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
