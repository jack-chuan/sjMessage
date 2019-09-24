package cn.com.sparknet.sjMessage.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.com.sparknet.sjMessage.search.domain.TrashMessage;

public interface TrashMessageRepository extends ElasticsearchRepository<TrashMessage, String> {

}
