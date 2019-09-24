package cn.com.sparknet.sjMessage.timer.mapper;

import cn.com.sparknet.sjMessage.timer.entity.SysTaskEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MSG定时任务表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-17 16:20:10
 */
@Mapper
public interface SysTaskMapper extends BaseMapper<SysTaskEntity> {

    List<SysTaskEntity> queryAllList();
	
}
