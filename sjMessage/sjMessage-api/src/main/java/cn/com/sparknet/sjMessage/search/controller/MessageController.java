package cn.com.sparknet.sjMessage.search.controller;

import java.util.ArrayList;
import java.util.List;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.sparknet.sjMessage.common.util.JsonResult;
import cn.com.sparknet.sjMessage.search.domain.QueryMessage;
import cn.com.sparknet.sjMessage.search.domain.TrashMessage;
import cn.com.sparknet.sjMessage.search.service.MessageService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/message")
@EnableSwagger2
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;

	/**
	 * 查询收件箱
	 * @param msg_title
	 * @param msg_type
	 * @param msg_content
	 * @param deptname
	 * @param orgname
	 * @param sender
	 * @param sdate
	 * @param edate
	 * @param pageNum
	 * @param pageSize
	 * @param mstate
	 * @param rstate
	 * @param if_read
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryReceiveMessage")
	public JsonResult<List<QueryMessage>> queryMessage(@RequestParam(value = "msg_title", required = false) String msg_title,
														@RequestParam(value = "msg_type", required = false) String msg_type,
														@RequestParam(value = "msg_content", required = false) String msg_content,
														@RequestParam(value = "deptname", required = false) String deptname,
														@RequestParam(value = "orgname", required = false) String orgname,
														@RequestParam(value = "sender", required = false) String sender,
														@RequestParam(value = "sdate", required = false) String sdate,
														@RequestParam(value = "edate", required = false) String edate,
														@RequestParam(value = "page") int pageNum,
														@RequestParam(value = "limit") int pageSize,
														@RequestParam(value = "mstate") String mstate,
														@RequestParam(value = "rstate") String rstate,
														@RequestParam(value = "if_read") String if_read,
//														@RequestParam(value = "receiver") String receiver,
                                                       HttpServletRequest request) throws Exception {
		List<QueryMessage> resultList = new ArrayList<QueryMessage>();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		JsonResult<List<QueryMessage>> jsonResult = null;
		try {
            RUser rUser = (RUser)request.getSession().getAttribute("rUser");
            String receiver = rUser.getUserId();
			//没有查询条件
			if((msg_title == null || "".equals(msg_title)) && (msg_type == null || "".equals(msg_type))
					&& (msg_content == null || "".equals(msg_content))
					&& (deptname == null || "".equals(deptname))
					&& (orgname == null || "".equals(orgname))
					&& (sender == null || "".equals(sender))
					&& (sdate == null || "".equals(sdate))
					&& (edate == null || "".equals(edate))
			) { //不输入查询条件时查询所有
				int count = messageService.queryRecMessageAllCount(receiver, mstate, rstate, if_read);
				resultList = messageService.queryRecMessageAll(pageNum, pageSize, mstate, rstate, if_read, receiver);
				jsonResult = new JsonResult<List<QueryMessage>>(resultList,"200","查询成功",count);
			}else { //有查询条件
				boolQueryBuilder = messageService.getBoolQueryBuilder(msg_title, msg_type, msg_content, deptname, orgname, sender, sdate, edate);
				int count = messageService.queryRecMessageByBoolQueryBuilderCount(boolQueryBuilder, pageNum, pageSize, mstate, rstate, if_read, receiver);
				resultList = messageService.queryRecMessageByBoolQueryBuilder(boolQueryBuilder, pageNum, pageSize, mstate, rstate, if_read, receiver);
				jsonResult = new JsonResult<List<QueryMessage>>(resultList,"200","查询成功",count);
			}
		}catch (Exception e) {
			jsonResult = new JsonResult<List<QueryMessage>>("500", "查询失败");
			logger.error(e.getMessage(), e);
		}
		return jsonResult;
	}
	
	/**
	 * 查询垃圾箱
	 * @param msg_title
	 * @param sdate
	 * @param edate
	 * @param pageNum
	 * @param pageSize
	 * @param mstate
	 * @param rstate
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryTrashMessage")
	public JsonResult<List<TrashMessage>> queryTrashMessage(@RequestParam(value = "msg_title", required = false) String msg_title,
														@RequestParam(value = "sdate", required = false) String sdate,
														@RequestParam(value = "edate", required = false) String edate,
														@RequestParam(value = "pageNum") int pageNum,
														@RequestParam(value = "pageSize") int pageSize,
														@RequestParam(value = "mstate") String mstate,
														@RequestParam(value = "rstate") String rstate,
                                                       HttpServletRequest request) throws Exception {
		List<TrashMessage> resultList = new ArrayList<TrashMessage>();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		JsonResult<List<TrashMessage>> jsonResult = null;
		try {
            RUser rUser = (RUser)request.getSession().getAttribute("rUser");
            String receiver = rUser.getUserId();
			//没有查询条件
			if((msg_title == null || "".equals(msg_title))
					&& (sdate == null || "".equals(sdate))
					&& (edate == null || "".equals(edate))
			) { //不输入查询条件时查询所有
				int count = messageService.queryTrashMessageAllCount(receiver, mstate, rstate);
				resultList = messageService.queryTrashMessageAll(pageNum, pageSize, mstate, rstate, receiver);
				jsonResult = new JsonResult<List<TrashMessage>>(resultList,"200","查询成功",count);
			}else { //有查询条件
				boolQueryBuilder = messageService.getBoolQueryBuilder(msg_title, sdate, edate);
				int count = messageService.queryTrashMessageByBoolQueryBuilderCount(boolQueryBuilder, pageNum, pageSize, mstate, rstate, receiver);
				resultList = messageService.queryTrashMessageByBoolQueryBuilder(boolQueryBuilder, pageNum, pageSize, mstate, rstate, receiver);
				jsonResult = new JsonResult<List<TrashMessage>>(resultList,"200","查询成功",count);
			}
		}catch (Exception e) {
			jsonResult = new JsonResult<List<TrashMessage>>("500", "查询失败");
			logger.error(e.getMessage(), e);
		}
		return jsonResult;
	}

}
