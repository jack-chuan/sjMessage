package cn.com.sparknet.sjMessage.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.com.sparknet.sjMessage.domain.QueryMessage;

public interface QueryMessageReporitory extends ElasticsearchRepository<QueryMessage, String> {

}
