package cn.com.sparknet.sjMessage.datalist.mapper.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainOrgEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MSG机构表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:27:04
 */
@Mapper
public interface MsMainOrgMapper extends BaseMapper<MsMainOrgEntity> {

    int insertList(List<MsMainOrgEntity> msMainOrgEntityList);

    int deleteAll();

    List<MsMainOrgEntity> queryAllList();

    boolean saveEntity(MsMainOrgEntity msMainOrgEntity);

    boolean updateByEntity(MsMainOrgEntity msMainOrgEntity);
}
