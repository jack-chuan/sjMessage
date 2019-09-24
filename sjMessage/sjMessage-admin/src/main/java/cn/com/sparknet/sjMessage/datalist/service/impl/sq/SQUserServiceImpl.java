package cn.com.sparknet.sjMessage.datalist.service.impl.sq;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sq.SQUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.sq.SQUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sqUserService")
public class SQUserServiceImpl extends ServiceImpl<SQUserMapper, SQUserEntity> implements SQUserService {

    @Autowired
    private SQUserMapper sqUserMapper;

    @Override
    public List<SQUserEntity> queryAllList() {
        try {
            return sqUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询宿迁用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<SQUserEntity> sqUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (SQUserEntity sqUser : sqUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(sqUser.getUserId());
                messageUserEntity.setUsername(sqUser.getUserStaffCode());
                messageUserEntity.setPersonId(sqUser.getPersonId());
                messageUserEntity.setDeptId(sqUser.getDeptId());
                messageUserEntity.setOrgId(sqUser.getOrgId());
                messageUserEntity.setUserType(sqUser.getUserType());
                messageUserEntity.setCreateDate(sqUser.getCreateDate());
                messageUserEntity.setOrd(sqUser.getOrd());
                messageUserEntity.setIsAllowLogin(sqUser.getIfAllowLogin() != null ? sqUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(sqUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换宿迁用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
