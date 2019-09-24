package cn.com.sparknet.sjMessage.app.mapper;

import java.util.List;
import java.util.Map;
import cn.com.sparknet.sjMessage.app.entity.MsgFile;
import org.apache.ibatis.annotations.Param;

public interface MsgFileMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(MsgFile record);

    int insertSelective(MsgFile record);

    MsgFile selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(MsgFile record);

    int updateByPrimaryKey(MsgFile record);
    
    /*
   	 * 获取消息附件列表
   	 */
    List<Map<String, Object>> selectByMsgId(String msgId);
    
    /*
   	 * 修改附件关联msgId
   	 */
    int updateFileByMsgId(@Param("msgId") String msgId, @Param("fileId") String fileId);
    
    /*
   	 * 通用附件删除（逻辑删除）
   	 */
    int updateFileStateByFileId(String fileId);
    
}