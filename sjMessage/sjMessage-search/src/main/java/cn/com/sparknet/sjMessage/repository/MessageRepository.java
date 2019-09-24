package cn.com.sparknet.sjMessage.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.com.sparknet.sjMessage.domain.Message;

public interface MessageRepository extends ElasticsearchRepository<Message, String> {

}
