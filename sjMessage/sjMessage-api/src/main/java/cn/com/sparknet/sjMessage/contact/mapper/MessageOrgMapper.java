package cn.com.sparknet.sjMessage.contact.mapper;


import cn.com.sparknet.sjMessage.contact.entity.MessageOrgEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG机构表
 *
 * @author zhanghm
 * @date 2019-05-20 09:16:01
 */
@Mapper
public interface MessageOrgMapper extends BaseMapper<MessageOrgEntity> {

    List<HashMap> selectOrgInfoByOrgId(String orgId);

    /**
     * @Description: zTree 一次性加载
     */
    List<HashMap> selectAllOrgChildrenByOrgParentId();

    List<Map<String,Object>> selectOrgInfo();
}
