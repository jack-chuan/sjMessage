package cn.com.sparknet.sjMessage.contact.service.impl;


import cn.com.sparknet.sjMessage.common.util.ZTreeResult;

import cn.com.sparknet.sjMessage.contact.entity.MessageOrgEntity;
import cn.com.sparknet.sjMessage.contact.mapper.MessageOrgMapper;
import cn.com.sparknet.sjMessage.contact.service.MessageOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("messageOrgService")
public class MessageOrgServiceImpl extends ServiceImpl<MessageOrgMapper, MessageOrgEntity> implements MessageOrgService {

    @Autowired
    private MessageOrgMapper messageOrgMapper;




    /**
    * @Description:  ORG 级联关系, zTree一次性加载
    * @Param: [parentOrgId]
    * @return: java.util.List<cn.com.sparknet.sjMessage.common.util.ZTreeResult>
    * @Author: Leo Lee
    * @Date: 2019/3/29
    */
    @Override
    public List<ZTreeResult> selectAllOrgChildrenByOrgParentId(){
        List<ZTreeResult> list = new ArrayList<>();
        List<HashMap> org = messageOrgMapper.selectAllOrgChildrenByOrgParentId();
        for (HashMap map:org){
			ZTreeResult zTreeResult = new ZTreeResult();
            zTreeResult.setId(map.get("ORG_ID").toString());
            if(map.get("PARENT_ORG_ID").equals("000000000000000000000000000000000876")){
				zTreeResult.setpId("000000000000000000000000000000000001");
			}else {
				zTreeResult.setpId(map.get("PARENT_ORG_ID").toString());
			}
            zTreeResult.setName(map.get("ORG_NAME").toString());
            list.add(zTreeResult);
        }
        return list;
    }

    @Override
    public List<Map<String,Object>> selectOrgInfo() {
        return messageOrgMapper.selectOrgInfo();
    }

}
