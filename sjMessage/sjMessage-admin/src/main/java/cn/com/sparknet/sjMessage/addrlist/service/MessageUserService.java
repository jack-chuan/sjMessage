package cn.com.sparknet.sjMessage.addrlist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.addrlist.entity.MessageUserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG用户表
 *
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
public interface MessageUserService extends IService<MessageUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
    * @Description: 用户信息
    */
    List<HashMap> selectUserInfoByUserId(String userId);
    
    /** 
    * @Description: 用户详细信息
    */ 
    List<HashMap> selectUserDetailByUserId(String userId);
    
    
}

