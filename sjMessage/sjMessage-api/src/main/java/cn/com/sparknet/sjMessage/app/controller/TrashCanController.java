package cn.com.sparknet.sjMessage.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.sparknet.sjMessage.app.service.TrashCanService;
import cn.com.sparknet.sjMessage.common.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/trashCan", produces = "application/json; charset=UTF-8")
@Api(value = "mybatis 实体类映射及相关信息查询", tags = "垃圾箱操作接口")
public class TrashCanController {

	@Autowired
	TrashCanService trashCanService;
	
	/*
	 * 获取垃圾箱消息列表
	 */
    @RequestMapping(value = "/queryTrashCanList", method = RequestMethod.POST)
    @ApiOperation(value = "查询垃圾箱列表", notes = "带分页：填写分页页码及分页大小")
    public JsonResult queryTrashCanList(@RequestParam("pageNum") @ApiParam("分页页码") int pageNum,
                                    @RequestParam("pageSize") @ApiParam("分页大小") int pageSize,
                                    /*@RequestParam("userId") @ApiParam("当前登录人ID") String userId,*/
                                    @RequestParam(value = "msgTitle", required = false) @ApiParam("消息标题") String msgTitle,
                                    @RequestParam(value = "receiveName", required = false) @ApiParam("收件人姓名") String receiveName,
                                    @RequestParam(value = "sendName", required = false) @ApiParam("发送人姓名") String sendName,
									@RequestParam(value = "sDate", required = false) @ApiParam("开始时间") String sDate,
									@RequestParam(value = "eDate", required = false) @ApiParam("结束时间") String eDate,
                                    HttpServletRequest request){
      List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
      Map<String, Object> map = new HashMap<String, Object>();
      int count = 0;
      JsonResult jsonResult;
      try {
            RUser rUser = (RUser)request.getSession().getAttribute("rUser");
            String userId = rUser.getUserId();
    	    list = trashCanService.queryTrashCanList(pageNum, pageSize, userId, msgTitle, receiveName, sendName, sDate, eDate);
    	    map = trashCanService.queryTrashCanListCount(pageNum, pageSize, userId, msgTitle, receiveName, sendName, sDate, eDate);
            count = Integer.parseInt(map.get("COUNTS").toString());
			jsonResult = new JsonResult(list,"200","查询成功",count);
		}catch(Exception e) {
			jsonResult = new JsonResult(list,"500","查询失败",count);
		}
      return jsonResult;
    }
    
    /*
	 * 垃圾箱消息删除
	 */
    @Transactional
    @RequestMapping(value = "/deleteTrashCan", method = RequestMethod.POST)
    @ApiOperation(value = "垃圾箱消息删除", notes = "消息接收、发送表Id,消息表主键msgId,消息接收、发送表type")
    public JsonResult deleteTrashCan(@RequestParam("id") @ApiParam("消息接收、发送表Id集合，以，隔开") String id){
      int count = 0;
      JsonResult jsonResult;
      String result = "false";
      try {
    	    count = trashCanService.deleteTrashCan(id);
    	    if(count > 0) {
    	    	result = "true";
    	    }else {
    	    	result = "false";
    	    }
			jsonResult = new JsonResult(result,"200","删除成功");
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult(result,"500","删除失败");
		}
      return jsonResult;
    }
    
    /*
	 * 垃圾箱消息恢复
	 */
    @Transactional
    @RequestMapping(value = "/removeTrashCan", method = RequestMethod.POST)
    @ApiOperation(value = "垃圾箱消息恢复", notes = "消息接收、发送表Id,消息表主键msgId,消息接收、发送表type")
    public JsonResult removeTrashCan(@RequestParam("id") @ApiParam("消息接收、发送表Id集合，以，隔开") String id,
									 @RequestParam("type") @ApiParam() String type){
      int count = 0;
      JsonResult jsonResult;
      String result = "false";
      try {
    	    count = trashCanService.removeTrashCan(id, type);
    	    if(count > 0) {
    	    	result = "true";
    	    }else {
    	    	result = "false";
    	    }
			jsonResult = new JsonResult(result,"200","恢复成功");
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult(result,"500","恢复失败");
		}
      return jsonResult;
    }
}
