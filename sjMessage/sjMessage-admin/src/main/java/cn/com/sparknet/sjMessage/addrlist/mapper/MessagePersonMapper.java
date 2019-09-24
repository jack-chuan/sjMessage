package cn.com.sparknet.sjMessage.addrlist.mapper;

import cn.com.sparknet.sjMessage.addrlist.entity.MessagePersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * MSG人员表
 * 
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@Mapper
public interface MessagePersonMapper extends BaseMapper<MessagePersonEntity> {

    List<HashMap> selectPersonInfoByPersonId(String personId);
}
