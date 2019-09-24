package cn.com.sparknet.sjMessage.app.aop.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController public class RedisController {
 * 
 * @Autowired private StringRedisTemplate redisTemplate;
 * 
 * @RequestMapping("/setValue") public String setValue() {
 * if(!redisTemplate.hasKey("shabao")) {
 * redisTemplate.opsForValue().append("shabao", "1111"); return
 * "使用redis缓存保存数据成功"; }else { redisTemplate.delete("shabao"); return "key已存在"; }
 * 
 * }
 * 
 * @RequestMapping("/getValue") public String getValue() {
 * if(!redisTemplate.hasKey("shabao")) { return "key不存在，请先保存数据"; }else { String
 * shabao = redisTemplate.opsForValue().get("shabao"); return
 * "获取到redis中的数据，shabao= "+shabao; } }
 * 
 * }
 */
