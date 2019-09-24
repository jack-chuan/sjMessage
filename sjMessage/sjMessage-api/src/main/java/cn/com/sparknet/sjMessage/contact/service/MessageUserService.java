package cn.com.sparknet.sjMessage.contact.service;

import cn.com.sparknet.sjMessage.common.util.ZTreeResult;

import java.util.HashMap;
import java.util.List;

public interface MessageUserService {

    List<HashMap> selectUserInfoByUserId(String userId);


    /**
     * @Description: zTree 一次性加载
     */
    List<ZTreeResult> selectAllUserChildrenByDeptId(String orgId);
}
