package cn.com.sparknet.sjMessage.app.mapper;

import cn.com.sparknet.sjMessage.app.entity.Message;
import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;

public interface MessageMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(MessageWithBLOBs record);

    int insertSelective(MessageWithBLOBs record);

    MessageWithBLOBs selectByPrimaryKey(String msgId);

    int updateByPrimaryKeySelective(MessageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MessageWithBLOBs record);

    int updateByPrimaryKey(Message record);
    
    int backMsg(String msgId);
}