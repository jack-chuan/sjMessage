package cn.com.sparknet.sjMessage.mq.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;

@Service
public class MessageProducer {

	private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
    private Environment env;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	public void sendMsg(MessageWithBLOBs messageWithBlobs) throws JsonProcessingException {
		logger.info("-----生产端发送消息开始-----消息标题："+messageWithBlobs.getMsgTitle());
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
		rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));
		Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(messageWithBlobs))
				.setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
		message.getMessageProperties()
			.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
		rabbitTemplate.convertAndSend(message);
		logger.info("-----生产端发送消息结束-----消息标题："+messageWithBlobs.getMsgTitle());
	}
	
	/**
	 * 发件箱发送消息
	 * @param messageWithBLOBs
	 * @throws JsonProcessingException
	 */
	public void sendMessage(MessageWithBLOBs messageWithBLOBs) throws JsonProcessingException {
		logger.info("-----生产端发送消息开始(高并发)-----消息标题："+messageWithBLOBs.getMsgTitle());
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.setExchange(env.getProperty("message.order.exchange.name"));
		rabbitTemplate.setRoutingKey(env.getProperty("message.order.routing.key.name"));
		Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(messageWithBLOBs))
				.setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
		message.getMessageProperties()
			.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
		rabbitTemplate.convertAndSend(message);
		logger.info("-----生产端发送消息结束(高并发)-----消息标题："+messageWithBLOBs.getMsgTitle());
	}
	
	/**
	 * 草稿箱发送消息
	 * @param messageWithBLOBs
	 * @throws JsonProcessingException
	 */
	public void draftSendMessage(MessageWithBLOBs messageWithBLOBs) throws JsonProcessingException {
		logger.info("-----生产端发送消息开始(高并发)-----消息标题："+messageWithBLOBs.getMsgTitle());
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.setExchange(env.getProperty("message.order.exchange.name"));
		rabbitTemplate.setRoutingKey(env.getProperty("message.order.routing.key.name"));
		Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(messageWithBLOBs))
				.setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
		message.getMessageProperties()
			.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
		rabbitTemplate.convertAndSend(message);
		logger.info("-----生产端发送消息结束(高并发)-----消息标题："+messageWithBLOBs.getMsgTitle());
	}
}
