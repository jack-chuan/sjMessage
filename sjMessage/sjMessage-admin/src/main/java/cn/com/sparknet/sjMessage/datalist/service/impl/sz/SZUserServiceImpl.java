package cn.com.sparknet.sjMessage.datalist.service.impl.sz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sz.SZUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.sz.SZUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("szUserService")
public class SZUserServiceImpl extends ServiceImpl<SZUserMapper, SZUserEntity> implements SZUserService {

    @Autowired
    private SZUserMapper szUserMapper;

    @Override
    public List<SZUserEntity> queryAllList() {
        try {
            return szUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询苏州用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<SZUserEntity> szUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (SZUserEntity szUser : szUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(szUser.getUserId());
                messageUserEntity.setUsername(szUser.getUserStaffCode());
                messageUserEntity.setPersonId(szUser.getPersonId());
                messageUserEntity.setDeptId(szUser.getDeptId());
                messageUserEntity.setOrgId(szUser.getOrgId());
                messageUserEntity.setUserType(szUser.getUserType());
                messageUserEntity.setCreateDate(szUser.getCreateDate());
                messageUserEntity.setOrd(szUser.getOrd());
                messageUserEntity.setIsAllowLogin(szUser.getIfAllowLogin() != null ? szUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(szUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换苏州用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
