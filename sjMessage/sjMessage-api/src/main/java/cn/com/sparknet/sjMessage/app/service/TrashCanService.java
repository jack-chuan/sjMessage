package cn.com.sparknet.sjMessage.app.service;

import java.util.List;
import java.util.Map;

public interface TrashCanService {

	/*
	 *  获取垃圾箱消息列表
	 */
    List<Map<String, Object>> queryTrashCanList(int pageNum, int pageSize, String userId, String msgTitle, String receiveName, String sendName, String sDate, String eDate);
    
    /*
	 *  垃圾箱删除消息
	 */
    int deleteTrashCan(String id);
    
    /*
	 *  垃圾箱消息恢复
	 */
    int removeTrashCan(String id, String type);
    
    Map<String, Object> queryTrashCanListCount(int pageNum, int pageSize, String userId, String msgTitle, String receiveName, String sendName, String sDate, String eDate);
}
