package cn.com.sparknet.sjMessage.contact.mapper;


import cn.com.sparknet.sjMessage.contact.entity.MessageDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG部门表
 *
 * @author zhanghm
 * @date 2019-05-20 09:16:01
 */
@Mapper
public interface MessageDeptMapper extends BaseMapper<MessageDeptEntity> {

    List<Map<String,Object>> selectDeptInfoByDeptId(String deptId);

    List<Map<String,Object>> selectDeptInfoByOrgId(@Param("orgId") String orgId);

    List<HashMap> selectAllDeptChildrenByOrgId();




}
