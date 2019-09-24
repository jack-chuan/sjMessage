package cn.com.sparknet.sjMessage.addrlist.service;

import cn.com.sparknet.sjMessage.common.util.ZTreeResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.addrlist.entity.MessageOrgEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG机构表
 *
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
public interface MessageOrgService extends IService<MessageOrgEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<HashMap> selectOrgInfoByOrgId(String orgId);

    /**
     * @Description: zTree 异步加载
     */
    List<ZTreeResult> selectOrgChildrenByOrgParentId(String parentOrgId);

    /**
     * @Description: zTree 一次性加载
     */
    List<ZTreeResult> selectAllOrgChildrenByOrgParentId();
}

