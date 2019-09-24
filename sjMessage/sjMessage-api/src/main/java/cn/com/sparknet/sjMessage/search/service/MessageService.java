package cn.com.sparknet.sjMessage.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import cn.com.sparknet.sjMessage.search.controller.ExtResultMapper;
import cn.com.sparknet.sjMessage.search.domain.QueryMessage;
import cn.com.sparknet.sjMessage.search.domain.TrashMessage;

@Service
public class MessageService {
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	private ExtResultMapper extResultMapper;
	
	/**
	 * 当没有查询条件时查询收件箱所有消息
	 * @param pageNum
	 * @param pageSize
	 * @param mstate
	 * @param rstate
	 * @param if_read
	 * @param receiver
	 * @return
	 */
	public List<QueryMessage> queryRecMessageAll(int pageNum,int pageSize,String mstate,String rstate,String if_read,String receiver) throws Exception {
		List<QueryMessage> resultList = new ArrayList<QueryMessage>();
		pageNum = pageNum - 1;
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		//不输入查询条件时查询所有
		NativeSearchQuery query = getNativeSearchQuery(pageNum, pageSize, nativeSearchQueryBuilder, receiver,mstate,rstate,if_read);
		Page<QueryMessage> page = elasticsearchTemplate.queryForPage(query, QueryMessage.class);
		resultList = page.getContent();
		return resultList;
	}
	
	/**
	 * 当没有查询条件时查询垃圾箱所有消息
	 * @param pageNum
	 * @param pageSize
	 * @param mstate
	 * @param rstate
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	public List<TrashMessage> queryTrashMessageAll(int pageNum,int pageSize,String mstate,String rstate,String receiver) throws Exception {
		List<TrashMessage> resultList = new ArrayList<TrashMessage>();
		pageNum = pageNum - 1;
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		//不输入查询条件时查询所有
		NativeSearchQuery query = getNativeSearchQuery(pageNum, pageSize, nativeSearchQueryBuilder, receiver,mstate,rstate);
		Page<TrashMessage> page = elasticsearchTemplate.queryForPage(query, TrashMessage.class);
		resultList = page.getContent();
		return resultList;
	}
	
	/**
	 * 当没有查询条件时查询收件箱消息条数
	 * @param receiver
	 * @param mstate
	 * @param rstate
	 * @param if_read
	 * @return
	 */
	public int queryRecMessageAllCount(String receiver,String mstate,String rstate,String if_read) throws Exception {
		Client client = elasticsearchTemplate.getClient();
		SearchRequestBuilder srb = client.prepareSearch("querymessage").setTypes("doc");
		SearchResponse sr = null;
		if("2".equals(if_read)) {
			sr = srb.setQuery(QueryBuilders.boolQuery()
					.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
					.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
					.must(QueryBuilders.matchPhraseQuery("rstate", rstate))).setSize(10000).execute().actionGet();
		}else {
			sr = srb.setQuery(QueryBuilders.boolQuery()
					.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
					.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
					.must(QueryBuilders.matchPhraseQuery("rstate", rstate))
					.must(QueryBuilders.matchPhraseQuery("if_read", if_read))).setSize(10000).execute().actionGet();
		}
		SearchHits hits = sr.getHits();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			Map<String, Object> source = hit.getSourceAsMap();
			list.add(source);
		}
		return list.size();
	}
	
	/**
	 * 当没有查询条件时查询垃圾箱消息条数
	 * @param receiver
	 * @param mstate
	 * @param rstate
	 * @return
	 * @throws Exception
	 */
	public int queryTrashMessageAllCount(String receiver,String mstate,String rstate) throws Exception {
		Client client = elasticsearchTemplate.getClient();
		SearchRequestBuilder srb = client.prepareSearch("trashmessage").setTypes("doc");
		SearchResponse sr = null;
			sr = srb.setQuery(QueryBuilders.boolQuery()
				.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
				.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
				.must(QueryBuilders.matchPhraseQuery("rstate", rstate))).setSize(10000).execute().actionGet();
		SearchHits hits = sr.getHits();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			Map<String, Object> source = hit.getSourceAsMap();
			list.add(source);
		}
		return list.size();
	}
	
	/**
	 * 当有查询条件时查询收件箱消息
	 * @param pageNum
	 * @param pageSize
	 * @param mstate
	 * @param rstate
	 * @param if_read
	 * @param receiver
	 * @return
	 */
	public List<QueryMessage> queryRecMessageByBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder,
																int pageNum,int pageSize,String mstate,String rstate,String if_read,String receiver) throws Exception {
		List<QueryMessage> resultList = new ArrayList<QueryMessage>();
		pageNum = pageNum - 1;
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		NativeSearchQuery query = getNativeSearchQueryWithBoolQuery(pageNum, pageSize, boolQueryBuilder,
																	nativeSearchQueryBuilder, receiver,mstate,rstate,if_read);
		//高亮显示入口
		Page<QueryMessage> page = elasticsearchTemplate.queryForPage(query, QueryMessage.class,extResultMapper);
		//不高亮显示入口
		//Page<QueryMessage> page = messageRepository.search(query);
		resultList = page.getContent();
		return resultList;
	}
	
	/**
	 * 当有查询条件时查询垃圾箱消息
	 * @param boolQueryBuilder
	 * @param pageNum
	 * @param pageSize
	 * @param mstate
	 * @param rstate
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	public List<TrashMessage> queryTrashMessageByBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder,
			int pageNum,int pageSize,String mstate,String rstate,String receiver) throws Exception {
		List<TrashMessage> resultList = new ArrayList<TrashMessage>();
		pageNum = pageNum - 1;
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		NativeSearchQuery query = getNativeSearchQueryWithBoolQuery(pageNum, pageSize, boolQueryBuilder,
						nativeSearchQueryBuilder, receiver,mstate,rstate);
		//高亮显示入口
		Page<TrashMessage> page = elasticsearchTemplate.queryForPage(query, TrashMessage.class,extResultMapper);
		//不高亮显示入口
		//Page<QueryMessage> page = messageRepository.search(query);
		resultList = page.getContent();
		return resultList;
}
	
	/**
	 * 当有查询条件时查询收件箱消息条数
	 * @param boolQueryBuilder
	 * @param pageNum
	 * @param pageSize
	 * @param mstate
	 * @param rstate
	 * @param if_read
	 * @param receiver
	 * @return
	 */
	public int queryRecMessageByBoolQueryBuilderCount(BoolQueryBuilder boolQueryBuilder,
			int pageNum,int pageSize,String mstate,String rstate,String if_read,String receiver) throws Exception {
		Client client = elasticsearchTemplate.getClient();
		SearchRequestBuilder srb = client.prepareSearch("querymessage").setTypes("doc");
		SearchResponse sr = null;
		if("2".equals(if_read)) {
			sr = srb.setQuery(boolQueryBuilder
					.filter(QueryBuilders.matchQuery("receiver", receiver))
					.filter(QueryBuilders.matchQuery("mstate", mstate))
					.filter(QueryBuilders.matchQuery("rstate", rstate))).setSize(10000).execute().actionGet();
		}else {
			sr = srb.setQuery(boolQueryBuilder
					.filter(QueryBuilders.matchQuery("receiver", receiver))
					.filter(QueryBuilders.matchQuery("mstate", mstate))
					.filter(QueryBuilders.matchQuery("rstate", rstate))
					.filter(QueryBuilders.matchQuery("if_read", if_read))).setSize(10000).execute().actionGet();
		}
		SearchHits hits = sr.getHits();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			Map<String, Object> source = hit.getSourceAsMap();
			list.add(source);
		}
		return list.size();
	}
	
	/**
	 * 当有查询条件时查询垃圾箱消息条数
	 * @param boolQueryBuilder
	 * @param pageNum
	 * @param pageSize
	 * @param mstate
	 * @param rstate
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	public int queryTrashMessageByBoolQueryBuilderCount(BoolQueryBuilder boolQueryBuilder,
			int pageNum,int pageSize,String mstate,String rstate,String receiver) throws Exception {
		Client client = elasticsearchTemplate.getClient();
		SearchRequestBuilder srb = client.prepareSearch("trashmessage").setTypes("doc");
		SearchResponse sr = null;
		sr = srb.setQuery(boolQueryBuilder
			.filter(QueryBuilders.matchQuery("receiver", receiver))
			.filter(QueryBuilders.matchQuery("mstate", mstate))
			.filter(QueryBuilders.matchQuery("srstate", rstate))).setSize(10000).execute().actionGet();
		SearchHits hits = sr.getHits();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			Map<String, Object> source = hit.getSourceAsMap();
			list.add(source);
		}
		return list.size();
	}
	
	/**
	 * 传参搜索（收件箱）
	 * @param pageNum
	 * @param pageSize
	 * @param bb
	 * @param nativeSearchQueryBuilder
	 * @param userId
	 * @return
	 */
	private NativeSearchQuery getNativeSearchQueryWithBoolQuery(int pageNum, int pageSize, BoolQueryBuilder boolQueryBuilder,
			NativeSearchQueryBuilder nativeSearchQueryBuilder, String receiver,String mstate,String rstate,String if_read) throws Exception {
		NativeSearchQuery query = null;
		if("2".equals(if_read)) {
			query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder)
					.withPageable(PageRequest.of(pageNum, pageSize))
					.withSort(SortBuilders.fieldSort("create_date").order(SortOrder.DESC))
					.withFilter(QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
						.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
						.must(QueryBuilders.matchPhraseQuery("rstate", rstate)))
					.withHighlightFields(
							 new HighlightBuilder.Field("msg_title").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("msg_type").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("msg_content").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("deptname").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("orgname").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("sender").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("create_date").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("create_date").preTags("<span style=\"color:red\">").postTags("</span>")
					)
					.build();
		}else {
			query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder)
					.withPageable(PageRequest.of(pageNum, pageSize))
					.withSort(SortBuilders.fieldSort("create_date").order(SortOrder.DESC))
					.withFilter(QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
						.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
						.must(QueryBuilders.matchPhraseQuery("rstate", rstate))
						.must(QueryBuilders.matchPhraseQuery("if_read", if_read)))
					.withHighlightFields(
							 new HighlightBuilder.Field("msg_title").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("msg_type").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("msg_content").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("deptname").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("orgname").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("sender").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("create_date").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("create_date").preTags("<span style=\"color:red\">").postTags("</span>")
					)
					.build();
		}
		return query;
	}
	
	/**
	 * 传参搜索（垃圾箱）
	 * @param pageNum
	 * @param pageSize
	 * @param boolQueryBuilder
	 * @param nativeSearchQueryBuilder
	 * @param receiver
	 * @param mstate
	 * @param rstate
	 * @return
	 * @throws Exception
	 */
	private NativeSearchQuery getNativeSearchQueryWithBoolQuery(int pageNum, int pageSize, BoolQueryBuilder boolQueryBuilder,
			NativeSearchQueryBuilder nativeSearchQueryBuilder, String receiver,String mstate,String rstate) throws Exception {
		NativeSearchQuery query = null;
			query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder)
					.withPageable(PageRequest.of(pageNum, pageSize))
					.withSort(SortBuilders.fieldSort("create_date").order(SortOrder.DESC))
					.withFilter(QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
						.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
						.must(QueryBuilders.matchPhraseQuery("srstate", rstate)))
					.withHighlightFields(
							 new HighlightBuilder.Field("msg_title").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("create_date").preTags("<span style=\"color:red\">").postTags("</span>")
							,new HighlightBuilder.Field("create_date").preTags("<span style=\"color:red\">").postTags("</span>")
					)
					.build();
		return query;
	}
	
	/**
	 * 不传参搜索(收件箱)
	 * @param pageNum
	 * @param pageSize
	 * @param nativeSearchQueryBuilder
	 * @param userId
	 * @return
	 */
	private NativeSearchQuery getNativeSearchQuery(int pageNum, int pageSize,NativeSearchQueryBuilder nativeSearchQueryBuilder,
												   String receiver,String mstate,String rstate,String if_read) throws Exception {
		NativeSearchQuery query = null;
		if("2".equals(if_read)) {
			query = nativeSearchQueryBuilder
					.withPageable(PageRequest.of(pageNum, pageSize))
					.withSort(SortBuilders.fieldSort("create_date").order(SortOrder.DESC))
					.withFilter(QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
						.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
						.must(QueryBuilders.matchPhraseQuery("rstate", rstate)))
					.build();
		}else {
			query = nativeSearchQueryBuilder
					.withPageable(PageRequest.of(pageNum, pageSize))
					.withSort(SortBuilders.fieldSort("create_date").order(SortOrder.DESC))
					.withFilter(QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
						.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
						.must(QueryBuilders.matchPhraseQuery("rstate", rstate))
						.must(QueryBuilders.matchPhraseQuery("if_read", if_read)))
					.build();
		}
		return query;
	}
	
	/**
	 * 不传参搜索（垃圾箱）
	 * @param pageNum
	 * @param pageSize
	 * @param nativeSearchQueryBuilder
	 * @param receiver
	 * @param mstate
	 * @param rstate
	 * @return
	 * @throws Exception
	 */
	private NativeSearchQuery getNativeSearchQuery(int pageNum, int pageSize,NativeSearchQueryBuilder nativeSearchQueryBuilder,
			   String receiver,String mstate,String rstate) throws Exception {
		NativeSearchQuery query = null;
			query = nativeSearchQueryBuilder
			.withPageable(PageRequest.of(pageNum, pageSize))
			.withSort(SortBuilders.fieldSort("create_date").order(SortOrder.DESC))
			.withFilter(QueryBuilders.boolQuery()
			.must(QueryBuilders.matchPhraseQuery("receiver", receiver))
			.must(QueryBuilders.matchPhraseQuery("mstate", mstate))
			.must(QueryBuilders.matchPhraseQuery("srstate", rstate)))
			.build();
		return query;
	}
	
	/**
	 * 查询参数封装（收件箱）
	 * @param msg_title
	 * @param msg_type
	 * @param msg_content
	 * @param deptname
	 * @param orgname
	 * @param sender
	 * @param sdate
	 * @param edate
	 * @author duyc
	 * @return
	 */
	public BoolQueryBuilder getBoolQueryBuilder(String msg_title,String msg_type,String msg_content,String deptname,
			String orgname,String sender,String sdate,String edate) throws Exception {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		if(msg_title != null && !"".equals(msg_title)) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("msg_title", "*"+msg_title+"*"));
		}
		if(msg_type != null && !"".equals(msg_type)) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("msg_type", "*"+msg_type+"*"));
		}
		if(msg_content != null && !"".equals(msg_content)) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("msg_content", "*"+msg_content+"*"));
		}
		if(deptname != null && !"".equals(deptname)) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("deptname", "*"+deptname+"*"));
		}
		if(orgname != null && !"".equals(orgname)) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("orgname", "*"+orgname+"*"));
		}
		if(sender != null && !"".equals(sender)) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("sender", "*"+sender+"*"));
		}
		if(sdate != null && !"".equals(sdate)) {
			boolQueryBuilder.must(QueryBuilders.rangeQuery("create_date").gte(sdate));
		}
		if(edate != null && !"".equals(edate)) {
			boolQueryBuilder.must(QueryBuilders.rangeQuery("create_date").lte(edate));
		}
		return boolQueryBuilder;
	}
	
	/**
	 * 查询参数封装（垃圾箱）
	 * @param msg_title
	 * @param sdate
	 * @param edate
	 * @return
	 * @throws Exception
	 */
	public BoolQueryBuilder getBoolQueryBuilder(String msg_title,String sdate,String edate) throws Exception {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		if(msg_title != null && !"".equals(msg_title)) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("msg_title", "*"+msg_title+"*"));
		}
		if(sdate != null && !"".equals(sdate)) {
			boolQueryBuilder.must(QueryBuilders.rangeQuery("create_date").gte(sdate));
		}
		if(edate != null && !"".equals(edate)) {
			boolQueryBuilder.must(QueryBuilders.rangeQuery("create_date").lte(edate));
		}
		return boolQueryBuilder;
	}
	
}
