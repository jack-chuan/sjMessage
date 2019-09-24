package cn.com.sparknet.sjMessage.datalist.service.impl.cz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.cz.CZUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.cz.CZUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("czUserService")
public class CZUserServiceImpl extends ServiceImpl<CZUserMapper, CZUserEntity> implements CZUserService {

    @Autowired
    private CZUserMapper czUserMapper;

    @Override
    public List<CZUserEntity> queryAllList() {
        try {
            return czUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询常州用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<CZUserEntity> czUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (CZUserEntity czUser : czUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(czUser.getUserId());
                messageUserEntity.setUsername(czUser.getUserStaffCode());
                messageUserEntity.setPersonId(czUser.getPersonId());
                messageUserEntity.setDeptId(czUser.getDeptId());
                messageUserEntity.setOrgId(czUser.getOrgId());
                messageUserEntity.setUserType(czUser.getUserType());
                messageUserEntity.setCreateDate(czUser.getCreateDate());
                messageUserEntity.setOrd(czUser.getOrd());
                messageUserEntity.setIsAllowLogin(czUser.getIfAllowLogin() != null ? czUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(czUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换常州用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
