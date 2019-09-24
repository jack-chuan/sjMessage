package cn.com.sparknet.sjMessage.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.com.sparknet.sjMessage.domain.ReceiveMsg;

public interface ReceiveMsgRepository extends ElasticsearchRepository<ReceiveMsg, String> {

}
