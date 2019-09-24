package cn.com.sparknet.sjMessage.timer.service.impl;

import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.common.util.Query;
import cn.com.sparknet.sjMessage.timer.entity.SysTaskEntity;
import cn.com.sparknet.sjMessage.timer.mapper.SysTaskMapper;
import cn.com.sparknet.sjMessage.timer.service.SysTaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysTaskService")
public class SysTaskServiceImpl extends ServiceImpl<SysTaskMapper, SysTaskEntity> implements SysTaskService {

    @Autowired
    private SysTaskMapper sysTaskMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysTaskEntity> page = this.page(
                new Query<SysTaskEntity>().getPage(params),
                new QueryWrapper<SysTaskEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SysTaskEntity> queryAllList() {
         return sysTaskMapper.queryAllList();
    }
}
