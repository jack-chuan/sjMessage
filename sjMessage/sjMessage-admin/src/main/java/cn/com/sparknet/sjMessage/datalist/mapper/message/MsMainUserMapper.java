package cn.com.sparknet.sjMessage.datalist.mapper.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * MSG用户表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:30:03
 */
@Mapper
public interface MsMainUserMapper extends BaseMapper<MsMainUserEntity> {

    int insertList(List<MsMainUserEntity> msMainUserEntityList);

    int deleteAll();

    List<MsMainUserEntity> queryAllList();

    boolean saveEntity(MsMainUserEntity msMainUserEntity);

    boolean updateByEntity(MsMainUserEntity msMainUserEntity);

    @Select(" select * from R$USER where username = #{username} ")
    MsMainUserEntity getMsMainUserEntity(String username);
}
