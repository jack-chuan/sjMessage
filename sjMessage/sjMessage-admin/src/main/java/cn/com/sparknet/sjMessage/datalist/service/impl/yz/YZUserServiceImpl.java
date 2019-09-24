package cn.com.sparknet.sjMessage.datalist.service.impl.yz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.yz.YZUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.yz.YZUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("yzUserService")
public class YZUserServiceImpl extends ServiceImpl<YZUserMapper, YZUserEntity> implements YZUserService {

    @Autowired
    private YZUserMapper yzUserMapper;

    @Override
    public List<YZUserEntity> queryAllList() {
        try {
            return yzUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询扬州用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<YZUserEntity> yzUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (YZUserEntity yzUser : yzUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(yzUser.getUserId());
                messageUserEntity.setUsername(yzUser.getUserStaffCode());
                messageUserEntity.setPersonId(yzUser.getPersonId());
                messageUserEntity.setDeptId(yzUser.getDeptId());
                messageUserEntity.setOrgId(yzUser.getOrgId());
                messageUserEntity.setUserType(yzUser.getUserType());
                messageUserEntity.setCreateDate(yzUser.getCreateDate());
                messageUserEntity.setOrd(yzUser.getOrd());
                messageUserEntity.setIsAllowLogin(yzUser.getIfAllowLogin() != null ? yzUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(yzUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换扬州用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
