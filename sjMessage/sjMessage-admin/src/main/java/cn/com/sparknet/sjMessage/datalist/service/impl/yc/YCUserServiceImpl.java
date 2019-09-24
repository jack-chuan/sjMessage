package cn.com.sparknet.sjMessage.datalist.service.impl.yc;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yc.YCUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.yc.YCUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.yc.YCUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("ycUserService")
public class YCUserServiceImpl extends ServiceImpl<YCUserMapper, YCUserEntity> implements YCUserService {

    @Autowired
    private YCUserMapper ycUserMapper;

    @Override
    public List<YCUserEntity> queryAllList() {
        try {
            return ycUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询盐城用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<YCUserEntity> ycUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (YCUserEntity ycUser : ycUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(ycUser.getUserId());
                messageUserEntity.setUsername(ycUser.getUserStaffCode());
                messageUserEntity.setPersonId(ycUser.getPersonId());
                messageUserEntity.setDeptId(ycUser.getDeptId());
                messageUserEntity.setOrgId(ycUser.getOrgId());
                messageUserEntity.setUserType(ycUser.getUserType());
                messageUserEntity.setCreateDate(ycUser.getCreateDate());
                messageUserEntity.setOrd(ycUser.getOrd());
                messageUserEntity.setIsAllowLogin(ycUser.getIfAllowLogin() != null ? ycUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(ycUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换盐城用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
