package cn.com.sparknet.sjMessage.datalist.mapper.ha;

import cn.com.sparknet.sjMessage.datalist.entity.ha.HAPersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 人员表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:45:12
 */
@Mapper
public interface HAPersonMapper extends BaseMapper<HAPersonEntity> {

    List<HAPersonEntity> queryAllList();
}
