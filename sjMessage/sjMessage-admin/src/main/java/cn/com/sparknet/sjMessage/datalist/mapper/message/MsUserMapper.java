package cn.com.sparknet.sjMessage.datalist.mapper.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MSG用户表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:30:03
 */
@Mapper
public interface MsUserMapper extends BaseMapper<MsUserEntity> {

    int insertList(List<MsUserEntity> messageOrgEntityList);

    int deleteAll();

    List<MsUserEntity> queryAllList();

    boolean saveEntity(MsUserEntity msUserEntity);

}
