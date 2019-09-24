package cn.com.sparknet.sjMessage.app.aop.redis.config;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;


/*
 * @Configuration
 * 
 * @EnableCaching public class RedisConfig extends CachingConfigurerSupport{
 * 
 * @Bean RedisMessageListenerContainer container(RedisConnectionFactory
 * connectionFactory, MessageListenerAdapter listenerAdapter) {
 * 
 * RedisMessageListenerContainer container = new
 * RedisMessageListenerContainer();
 * container.setConnectionFactory(connectionFactory);
 * container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
 * 
 * return container; }
 * 
 * @Bean public MessageListenerAdapter adapter(Receiver receiver) { return new
 * MessageListenerAdapter(receiver,"receiveMessage"); }
 * 
 * @Bean Receiver receiver(CountDownLatch latch) { return new Receiver(latch); }
 * 
 * @Bean public CountDownLatch latch() { return new CountDownLatch(1); }
 * 
 * @Bean public StringRedisTemplate redisTemplate(RedisConnectionFactory
 * factory) { return new StringRedisTemplate(factory); }
 * 
 * public class Receiver {
 * 
 * private CountDownLatch downLatch;
 * 
 * @Autowired public Receiver(CountDownLatch downLatch) { this.downLatch =
 * downLatch; }
 * 
 * public void receiveMessage(String message) { downLatch.countDown(); } } }
 */
