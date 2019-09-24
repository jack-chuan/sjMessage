package cn.com.sparknet.sjMessage.contact.service.impl;


import cn.com.sparknet.sjMessage.contact.entity.MessageDeptEntity;
import cn.com.sparknet.sjMessage.contact.mapper.MessageDeptMapper;
import cn.com.sparknet.sjMessage.contact.service.MessageDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("messageDeptService")
public class MessageDeptServiceImpl extends ServiceImpl<MessageDeptMapper, MessageDeptEntity> implements MessageDeptService {

    @Autowired
    private MessageDeptMapper messageDeptMapper;


    @Override
    public List<Map<String,Object>> selectDeptInfoByOrgId(String orgId) {
        return messageDeptMapper.selectDeptInfoByOrgId(orgId);
    }

}
