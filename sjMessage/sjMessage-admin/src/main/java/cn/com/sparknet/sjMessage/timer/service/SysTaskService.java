package cn.com.sparknet.sjMessage.timer.service;

import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.timer.entity.SysTaskEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * MSG定时任务表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-17 16:20:10
 */
public interface SysTaskService extends IService<SysTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysTaskEntity> queryAllList();

}

