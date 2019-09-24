package cn.com.sparknet.sjMessage.datalist.service.impl.zj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.zj.ZJUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.zj.ZJUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("zjUserService")
public class ZJUserServiceImpl extends ServiceImpl<ZJUserMapper, ZJUserEntity> implements ZJUserService {

    @Autowired
    private ZJUserMapper zjUserMapper;

    @Override
    public List<ZJUserEntity> queryAllList() {
        try {
            return zjUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询镇江用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<ZJUserEntity> zjUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (ZJUserEntity zjUser : zjUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(zjUser.getUserId());
                messageUserEntity.setUsername(zjUser.getUserStaffCode());
                messageUserEntity.setPersonId(zjUser.getPersonId());
                messageUserEntity.setDeptId(zjUser.getDeptId());
                messageUserEntity.setOrgId(zjUser.getOrgId());
                messageUserEntity.setUserType(zjUser.getUserType());
                messageUserEntity.setCreateDate(zjUser.getCreateDate());
                messageUserEntity.setOrd(zjUser.getOrd());
                messageUserEntity.setIsAllowLogin(zjUser.getIfAllowLogin() != null ? zjUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(zjUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换镇江用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
