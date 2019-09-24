package cn.com.sparknet.sjMessage.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.com.sparknet.sjMessage.domain.SendMsg;

public interface SendMsgRepository extends ElasticsearchRepository<SendMsg, String> {

}
