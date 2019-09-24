package cn.com.sparknet.sjMessage.datalist.mapper.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MSG人员表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:28:10
 */
@Mapper
public interface MsPersonMapper extends BaseMapper<MsPersonEntity> {

    int insertList(List<MsPersonEntity> messageOrgEntityList);

    int deleteAll();

    List<MsPersonEntity> queryAllList();

    boolean saveEntity(MsPersonEntity msPersonEntity);

}
