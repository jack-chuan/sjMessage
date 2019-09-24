package cn.com.sparknet.sjMessage.app.mapper;

import java.util.List;
import java.util.Map;

import cn.com.sparknet.sjMessage.app.entity.SendMsg;
import org.apache.ibatis.annotations.Param;

public interface SendMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(SendMsg record);

    int insertSelective(SendMsg record);

    SendMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SendMsg record);

    int updateByPrimaryKey(SendMsg record);
    
    /**
     *  查询发件箱列表
     * @param sender
     * @param state
     * @return
     * @author duyc
     */
    List<Map<String,Object>> querySendMsgList(@Param("sender") String sender, @Param("state") String state, @Param("msgTitle") String msgTitle,@Param("receiveName") String receiveName,@Param("sendName") String sendName,@Param("sDate") String sDate,@Param("eDate") String eDate);
    
    
    Map<String,Object> querySendMsgListCount(@Param("sender") String sender, @Param("state") String state, @Param("msgTitle") String msgTitle,@Param("receiveName") String receiveName,@Param("sendName") String sendName,@Param("sDate") String sDate,@Param("eDate") String eDate);
	 /**
	     * 查询发件箱消息详情
	  * @param msgId
	  * @return
	  * @author duyc
	 */
    Map<String,Object> queryMsgDetailByMsgId(String msg_id);
    
    int deleteSendMsg(@Param("state") String state,@Param("id") String id);
    
    /*
	 *  获取草稿箱消息列表
	 */
    List<Map<String, Object>> queryDraftBoxList(@Param("sender") String sender,@Param("msgTitle") String msgTitle,@Param("receiveName") String receiveName,@Param("sendName") String sendName,@Param("sDate") String sDate,@Param("eDate") String eDate);
    
    /*
     * 根据消息主键msgId查询发送表
     */
    SendMsg selectByMsgId(String msgId);
    
    /*
	 *  获取垃圾箱消息列表
	 */
    List<Map<String, Object>> queryTrashCanList(@Param("userId") String userId,@Param("msgTitle") String msgTitle,@Param("receiveName") String receiveName,@Param("sendName") String sendName,@Param("sDate") String sDate,@Param("eDate") String eDate);
    
    
    Map<String, Object> queryDraftBoxListCount(@Param("sender") String sender,@Param("msgTitle") String msgTitle,@Param("receiveName") String receiveName,@Param("sendName") String sendName,@Param("sDate") String sDate,@Param("eDate") String eDate);
    
    
    Map<String, Object> queryTrashCanListCount(@Param("userId") String userId,@Param("msgTitle") String msgTitle,@Param("receiveName") String receiveName,@Param("sendName") String sendName,@Param("sDate") String sDate,@Param("eDate") String eDate);
}