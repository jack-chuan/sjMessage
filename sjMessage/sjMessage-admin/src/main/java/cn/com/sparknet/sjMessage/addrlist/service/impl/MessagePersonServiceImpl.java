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

import cn.com.sparknet.sjMessage.addrlist.mapper.MessagePersonMapper;
import cn.com.sparknet.sjMessage.addrlist.entity.MessagePersonEntity;
import cn.com.sparknet.sjMessage.addrlist.service.MessagePersonService;


@Service("messagePersonService")
public class MessagePersonServiceImpl extends ServiceImpl<MessagePersonMapper, MessagePersonEntity> implements MessagePersonService {

    @Autowired
    private MessagePersonMapper messagePersonMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MessagePersonEntity> page = this.page(
                new Query<MessagePersonEntity>().getPage(params),
                new QueryWrapper<MessagePersonEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<HashMap> selectPersonInfoByPersonId(String personId){
        return messagePersonMapper.selectPersonInfoByPersonId(personId);
    }

}
