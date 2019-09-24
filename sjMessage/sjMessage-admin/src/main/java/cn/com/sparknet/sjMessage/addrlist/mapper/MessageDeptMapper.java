package cn.com.sparknet.sjMessage.addrlist.mapper;

import cn.com.sparknet.sjMessage.addrlist.entity.MessageDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * MSG部门表
 * 
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@Mapper
public interface MessageDeptMapper extends BaseMapper<MessageDeptEntity> {

    List<HashMap> selectDeptInfoByDeptId(@Param("deptId")String deptId);
}
