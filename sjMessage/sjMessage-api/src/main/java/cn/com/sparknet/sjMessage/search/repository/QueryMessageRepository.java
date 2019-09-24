package cn.com.sparknet.sjMessage.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.com.sparknet.sjMessage.search.domain.QueryMessage;


public interface QueryMessageRepository extends ElasticsearchRepository<QueryMessage, String> {

}
