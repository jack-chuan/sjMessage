package cn.com.sparknet.sjMessage.contact.service;

import cn.com.sparknet.sjMessage.common.util.ZTreeResult;
import cn.com.sparknet.sjMessage.contact.entity.MessageOrgEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * MSG机构表
 *
 * @author zhanghm
 * @date 2019-05-20 09:16:01
 */
public interface MessageOrgService extends IService<MessageOrgEntity> {



    /**
     * @Description: zTree 一次性加载
     */
    List<ZTreeResult> selectAllOrgChildrenByOrgParentId();


    List<Map<String,Object>> selectOrgInfo();


}

