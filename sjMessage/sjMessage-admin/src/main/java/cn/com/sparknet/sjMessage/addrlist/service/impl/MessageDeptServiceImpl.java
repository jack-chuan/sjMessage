package cn.com.sparknet.sjMessage.addrlist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.common.util.Query;

import cn.com.sparknet.sjMessage.addrlist.mapper.MessageDeptMapper;
import cn.com.sparknet.sjMessage.addrlist.entity.MessageDeptEntity;
import cn.com.sparknet.sjMessage.addrlist.service.MessageDeptService;


@Service("messageDeptService")
public class MessageDeptServiceImpl extends ServiceImpl<MessageDeptMapper, MessageDeptEntity> implements MessageDeptService {

    @Autowired
    private MessageDeptMapper messageDeptMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MessageDeptEntity> page = this.page(
                new Query<MessageDeptEntity>().getPage(params),
                new QueryWrapper<MessageDeptEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<HashMap> selectDeptInfoByDeptId(String deptId){
        return messageDeptMapper.selectDeptInfoByDeptId(deptId);
    }

}
