package cn.com.sparknet.sjMessage.mq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import cn.com.sparknet.sjMessage.mq.client.MessageOrderListener;

/**
 * rabbitmq配置类
 * @author duyc
 *
 */
@Configuration
public class RabbitmqConfig {

	private static final Logger logger = LoggerFactory.getLogger(RabbitmqConfig.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CachingConnectionFactory connectionFactory;
	
	@Autowired
	private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
	
	/**
     * 单一消费者
     * @return SimpleRabbitListenerContainerFactory
     */
	@Bean(name = "singleListenerContainer")
	public SimpleRabbitListenerContainerFactory listenerContainer() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setConcurrentConsumers(1);
		factory.setMaxConcurrentConsumers(1);
		factory.setPrefetchCount(1);
		factory.setTxSize(1);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}
	
	/**
	 * 多个消费者
	 * @return SimpleRabbitListenerContainerFactory
	 */
	@Bean(name = "multiListenerContainer")
	public SimpleRabbitListenerContainerFactory mutiListenerContainer() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factoryConfigurer.configure(factory, connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setAcknowledgeMode(AcknowledgeMode.NONE);
		factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency",int.class));
		factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency",int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch",int.class));
		return factory;
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		connectionFactory.setPublisherConfirms(true);
		connectionFactory.setPublisherReturns(true);
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMandatory(true);
		/**
		 * 成功
		 */
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				logger.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
			}
		});
		/**
		 * 失败
		 */
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				logger.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
			}
		});
		return rabbitTemplate;
	}
	//单请求处理示例开始
	@Bean
	public Queue logUserQueue() {
		return new Queue(env.getProperty("log.user.queue.name"),true);
	}
	
	@Bean
	public DirectExchange logUserExchange() {
		return new DirectExchange(env.getProperty("log.user.exchange.name"), true, false);
	}
	
	@Bean
	public Binding logUserBinding() {
		return BindingBuilder.bind(logUserQueue()).to(logUserExchange()).with(env.getProperty("log.user.routing.key.name"));
	}
	//单请求处理示例结束
	
	//高并发请求示例开始(消息系统用此示例)
	
	@Autowired
	private MessageOrderListener messageOrderListener;
	
	@Bean
	public Queue messageOrderQueue() {
		return new Queue(env.getProperty("message.order.queue.name"),true);
	}
	
	@Bean
	public TopicExchange messageOrderExchange() {
		return new TopicExchange(env.getProperty("message.order.exchange.name"), true, false);
	}
	
	@Bean
	public Binding messageOrderBinding() {
		return BindingBuilder.bind(messageOrderQueue()).to(messageOrderExchange()).with(env.getProperty("message.order.routing.key.name"));
	}
	
	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setMessageConverter(new Jackson2JsonMessageConverter());
		//并发配置
		container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency",Integer.class));
		container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency",Integer.class));
		container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch",Integer.class));
		//消息确认机制
		container.setQueues(messageOrderQueue());
		container.setMessageListener(messageOrderListener);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		return container;
	}
	//高并发请求示例结束
}
