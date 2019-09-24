package cn.com.sparknet.sjMessage.datalist.service.impl.tz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.tz.TZUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.tz.TZUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("tzUserService")
public class TZUserServiceImpl extends ServiceImpl<TZUserMapper, TZUserEntity> implements TZUserService {

    @Autowired
    private TZUserMapper tzUserMapper;

    @Override
    public List<TZUserEntity> queryAllList() {
        try {
            return tzUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询泰州用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<TZUserEntity> tzUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (TZUserEntity tzUser : tzUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(tzUser.getUserId());
                messageUserEntity.setUsername(tzUser.getUserStaffCode());
                messageUserEntity.setPersonId(tzUser.getPersonId());
                messageUserEntity.setDeptId(tzUser.getDeptId());
                messageUserEntity.setOrgId(tzUser.getOrgId());
                messageUserEntity.setUserType(tzUser.getUserType());
                messageUserEntity.setCreateDate(tzUser.getCreateDate());
                messageUserEntity.setOrd(tzUser.getOrd());
                messageUserEntity.setIsAllowLogin(tzUser.getIfAllowLogin() != null ? tzUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(tzUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换泰州用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
