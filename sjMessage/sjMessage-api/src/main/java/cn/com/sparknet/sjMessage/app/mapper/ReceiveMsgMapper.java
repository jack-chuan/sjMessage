package cn.com.sparknet.sjMessage.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.sparknet.sjMessage.app.entity.ReceiveMsg;

public interface ReceiveMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReceiveMsg record);

    int insertSelective(ReceiveMsg record);

    Map<String, Object> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiveMsg record);

    int updateByPrimaryKey(ReceiveMsg record);
    
    /*
	 * 获取收件箱已读未读列表
	 */
    List<Map<String, Object>> queryInBoxList(@Param("state") String state, @Param("receiver") String receiver,@Param("ifRead") String ifRead,@Param("msgTitle") String msgTitle,@Param("receiveName") String receiveName,@Param("sendName") String sendName);
    
    /*
	 * 获取收件箱已读未读列表条数
	 */
    Map<String, Object> queryInBoxListCount(@Param("state") String state, @Param("receiver") String receiver,@Param("ifRead") String ifRead,@Param("msgTitle") String msgTitle,@Param("receiveName") String receiveName,@Param("sendName") String sendName);
    
    /*
	 * 标记为已读未读
	 */
	int signRead(@Param("id") String id, @Param("ifRead") String ifRead);
	
	/*
	 * 收件箱消息删除
	 */
	int delInBoxMsg(@Param("state") String state, @Param("id") String id);
	
	/*
	 * 根据用户ID查询用户名
	 */
	Map<String, Object> getUserName(String userId);
	
	/*
	 * 获取选中的机构、部门、人员所有的人员ID集合
	 */
    List<Map<String, Object>> querySendUserList(String id);
}