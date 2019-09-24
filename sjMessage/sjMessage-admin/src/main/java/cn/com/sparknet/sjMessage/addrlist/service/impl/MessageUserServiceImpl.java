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

import cn.com.sparknet.sjMessage.addrlist.mapper.MessageUserMapper;
import cn.com.sparknet.sjMessage.addrlist.entity.MessageUserEntity;
import cn.com.sparknet.sjMessage.addrlist.service.MessageUserService;


@Service("messageUserService")
public class MessageUserServiceImpl extends ServiceImpl<MessageUserMapper, MessageUserEntity> implements MessageUserService {

    @Autowired
    private MessageUserMapper messageUserMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MessageUserEntity> page = this.page(
                new Query<MessageUserEntity>().getPage(params),
                new QueryWrapper<MessageUserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @Description: 用户信息
     */
    @Override
    public List<HashMap> selectUserInfoByUserId(String userId){
        return messageUserMapper.selectUserInfoByUserId(userId);
    }

    /**
     * @Description: 用户详细信息
     */
    @Override
    public List<HashMap> selectUserDetailByUserId(String userId){
        return messageUserMapper.selectUserDetailByUserId(userId);
    }



}
