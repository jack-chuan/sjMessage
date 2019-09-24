package cn.com.sparknet.sjMessage.datalist.service.impl.lyg;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.lyg.LYGUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.lyg.LYGUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("lygUserService")
public class LYGUserServiceImpl extends ServiceImpl<LYGUserMapper, LYGUserEntity> implements LYGUserService {

    @Autowired
    private LYGUserMapper lygUserMapper;

    @Override
    public List<LYGUserEntity> queryAllList() {
        try {
            return lygUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询连云港用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<LYGUserEntity> lygUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (LYGUserEntity lygUser : lygUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(lygUser.getUserId());
                messageUserEntity.setUsername(lygUser.getUserStaffCode());
                messageUserEntity.setPersonId(lygUser.getPersonId());
                messageUserEntity.setDeptId(lygUser.getDeptId());
                messageUserEntity.setOrgId(lygUser.getOrgId());
                messageUserEntity.setUserType(lygUser.getUserType());
                messageUserEntity.setCreateDate(lygUser.getCreateDate());
                messageUserEntity.setOrd(lygUser.getOrd());
                messageUserEntity.setIsAllowLogin(lygUser.getIfAllowLogin() != null ? lygUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(lygUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换连云港用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
