package cn.com.sparknet.sjMessage.addrlist.service.impl;

import cn.com.sparknet.sjMessage.common.util.ZTreeResult;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.common.util.Query;

import cn.com.sparknet.sjMessage.addrlist.mapper.MessageOrgMapper;
import cn.com.sparknet.sjMessage.addrlist.entity.MessageOrgEntity;
import cn.com.sparknet.sjMessage.addrlist.service.MessageOrgService;


@Service("messageOrgService")
public class MessageOrgServiceImpl extends ServiceImpl<MessageOrgMapper, MessageOrgEntity> implements MessageOrgService {

    @Autowired
    private MessageOrgMapper messageOrgMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MessageOrgEntity> page = this.page(
                new Query<MessageOrgEntity>().getPage(params),
                new QueryWrapper<MessageOrgEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<HashMap> selectOrgInfoByOrgId(String orgId){
        return messageOrgMapper.selectOrgInfoByOrgId(orgId);
    }

    /** 
    * @Description: ORG 级联关系, zTree异步加载
     * MapKey：ORG_ID, PARENT_ORG_ID, ORG_NAME
    * @Param: [parentOrgId] 
    * @return: java.util.List<java.util.HashMap> 
    * @Author: Leo Lee
    * @Date: 2019/3/29 
    */ 
    @Override
    public List<ZTreeResult> selectOrgChildrenByOrgParentId(String parentOrgId){
        List<ZTreeResult> list = new ArrayList<>();
        List<HashMap> relation = messageOrgMapper.selectOrgChildrenByOrgParentId(parentOrgId);

        
        for (HashMap map:relation){

            ZTreeResult zTreeResult = new ZTreeResult();
            zTreeResult.setId(map.get("ORG_ID").toString());
            zTreeResult.setpId(map.get("PARENT_ORG_ID").toString());
            zTreeResult.setName(map.get("ORG_NAME").toString());
            int count = messageOrgMapper.selectCountByOrgParentId(map.get("ORG_ID").toString());
            if (count>0){
                zTreeResult.setIsParent("true");
            }else {
                zTreeResult.setIsParent("false");
            }
            list.add(zTreeResult);
        }
        return list;
    }

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
        List<HashMap> relation = messageOrgMapper.selectAllOrgChildrenByOrgParentId();

        for (HashMap map:relation){
            ZTreeResult zTreeResult = new ZTreeResult();
            zTreeResult.setId(map.get("ORG_ID").toString());
            zTreeResult.setpId(map.get("PARENT_ORG_ID").toString());
            zTreeResult.setName(map.get("ORG_NAME").toString());
            list.add(zTreeResult);
        }
        return list;
    }

}
