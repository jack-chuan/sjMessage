package cn.com.sparknet.sjMessage.datalist.service.impl.xz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.xz.XZUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.xz.XZUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.xz.XZUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("xzUserService")
public class XZUserServiceImpl extends ServiceImpl<XZUserMapper, XZUserEntity> implements XZUserService {

    @Autowired
    private XZUserMapper xzUserMapper;

    @Override
    public List<XZUserEntity> queryAllList() {
        try {
            return xzUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询徐州用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<XZUserEntity> xzUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (XZUserEntity xzUser : xzUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(xzUser.getUserId());
                messageUserEntity.setUsername(xzUser.getUserStaffCode());
                messageUserEntity.setPersonId(xzUser.getPersonId());
                messageUserEntity.setDeptId(xzUser.getDeptId());
                messageUserEntity.setOrgId(xzUser.getOrgId());
                messageUserEntity.setUserType(xzUser.getUserType());
                messageUserEntity.setCreateDate(xzUser.getCreateDate());
                messageUserEntity.setOrd(xzUser.getOrd());
                messageUserEntity.setIsAllowLogin(xzUser.getIfAllowLogin() != null ? xzUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(xzUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换徐州用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
