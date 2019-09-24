package cn.com.sparknet.sjMessage.contact.service;

import cn.com.sparknet.sjMessage.contact.entity.MessageDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG部门表
 *
 * @author zhanghm
 * @date 2019-05-20 09:16:01
 */
public interface MessageDeptService extends IService<MessageDeptEntity> {

    List<Map<String,Object>> selectDeptInfoByOrgId(String orgId);
}

