package cn.com.sparknet.sjMessage.datalist.service.impl.ha;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.ha.HAUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.ha.HAUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.ha.HAUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("haUserService")
public class HAUserServiceImpl extends ServiceImpl<HAUserMapper, HAUserEntity> implements HAUserService {

    @Autowired
    private HAUserMapper haUserMapper;

    @Override
    public List<HAUserEntity> queryAllList() {
        try {
            return haUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询淮安用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<HAUserEntity> haUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (HAUserEntity haUser : haUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(haUser.getUserId());
                messageUserEntity.setUsername(haUser.getUserStaffCode());
                messageUserEntity.setPersonId(haUser.getPersonId());
                messageUserEntity.setDeptId(haUser.getDeptId());
                messageUserEntity.setOrgId(haUser.getOrgId());
                messageUserEntity.setUserType(haUser.getUserType());
                messageUserEntity.setCreateDate(haUser.getCreateDate());
                messageUserEntity.setOrd(haUser.getOrd());
                messageUserEntity.setIsAllowLogin(haUser.getIfAllowLogin() != null ? haUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(haUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换淮安用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
