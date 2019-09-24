package cn.com.sparknet.sjMessage.addrlist.mapper;

import cn.com.sparknet.sjMessage.addrlist.entity.MessageOrgEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * MSG机构表
 * 
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@Mapper
public interface MessageOrgMapper extends BaseMapper<MessageOrgEntity> {

    List<HashMap> selectOrgInfoByOrgId(String orgId);

    /**
    * @Description: zTree 异步加载
    */
    List<HashMap> selectOrgChildrenByOrgParentId(String parentOrgId);
    int selectCountByOrgParentId(String parentOrgId);

    /**
     * @Description: zTree 一次性加载
     */
    List<HashMap> selectAllOrgChildrenByOrgParentId();
}
