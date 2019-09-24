package cn.com.sparknet.sjMessage.contact.service.impl;

import cn.com.sparknet.sjMessage.common.util.ZTreeResult;
import cn.com.sparknet.sjMessage.contact.entity.MessageUserEntity;
import cn.com.sparknet.sjMessage.contact.mapper.MessageDeptMapper;
import cn.com.sparknet.sjMessage.contact.mapper.MessageOrgMapper;
import cn.com.sparknet.sjMessage.contact.mapper.MessageUserMapper;
import cn.com.sparknet.sjMessage.contact.service.MessageUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("messageUserService")
public class MessageUserServiceImpl extends ServiceImpl<MessageUserMapper, MessageUserEntity> implements MessageUserService {

    @Autowired
    private MessageUserMapper messageUserMapper;

    @Autowired
    private MessageDeptMapper messageDeptMapper;

    @Autowired
    private MessageOrgMapper messageOrgMapper;

    @Override
    public List<HashMap> selectUserInfoByUserId(String userId) {
        return messageUserMapper.selectUserInfoByUserId(userId);
    }

    @Override
    public List<ZTreeResult> selectAllUserChildrenByDeptId(String orgId) {
        List<ZTreeResult> list = new ArrayList<>();
        String[] orgIds = orgId.split(",");
        for(int i = 0; i < orgIds.length; i++) {
            List<HashMap> org = messageOrgMapper.selectOrgInfoByOrgId(orgIds[i]);
            List<Map<String,Object>> dept = messageDeptMapper.selectDeptInfoByOrgId(orgIds[i]);

            for (HashMap map : org) {
                ZTreeResult zTreeResult = new ZTreeResult();
                zTreeResult.setId(map.get("ORG_ID").toString());
                zTreeResult.setpId(map.get("ORG_ID").toString());
                zTreeResult.setName(map.get("ORG_NAME").toString());
                list.add(zTreeResult);
            }
            for (Map map : dept) {
                ZTreeResult zTreeResult = new ZTreeResult();
                zTreeResult.setId(map.get("DEPT_ID").toString());
                zTreeResult.setpId(map.get("ORG_ID").toString());
                zTreeResult.setName(map.get("DEPT_NAME").toString());
                list.add(zTreeResult);
            }
            for (Map map : dept) {
                String deptId = map.get("DEPT_ID").toString();
                List<HashMap> user = messageUserMapper.selectUserInfoByDeptId(deptId);
                for (HashMap map2 : user) {
                    ZTreeResult zTreeResult = new ZTreeResult();
                    zTreeResult.setId(map2.get("USER_ID").toString());
                    zTreeResult.setpId(map2.get("DEPT_ID").toString());
                    zTreeResult.setName(map2.get("PERSON_NAME").toString());
                    list.add(zTreeResult);
                }
            }
        }
        return list;
    }
}
