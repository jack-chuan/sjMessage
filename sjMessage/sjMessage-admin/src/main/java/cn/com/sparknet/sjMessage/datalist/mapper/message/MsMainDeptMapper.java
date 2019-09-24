package cn.com.sparknet.sjMessage.datalist.mapper.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MSG部门表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-13 10:21:05
 */
@Mapper
public interface MsMainDeptMapper extends BaseMapper<MsMainDeptEntity> {

    int insertList(List<MsMainDeptEntity> msMainDeptEntityList);

    Integer queryAllCount();

    int deleteAll();

    List<MsMainDeptEntity> queryAllList();

    boolean saveEntity(MsMainDeptEntity msMainDeptEntity);

    boolean updateByEntity(MsMainDeptEntity msMainDeptEntity);
}
