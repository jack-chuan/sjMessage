package cn.com.sparknet.sjMessage.datalist.service.impl.nj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.nj.NJUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.nj.NJUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("njUserService")
public class NJUserServiceImpl extends ServiceImpl<NJUserMapper, NJUserEntity> implements NJUserService {

    @Autowired
    private NJUserMapper njUserMapper;

    @Override
    public List<NJUserEntity> queryAllList() {
        try {
            return njUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询南京用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<NJUserEntity> njUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (NJUserEntity njUser : njUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(njUser.getUserId());
                messageUserEntity.setUsername(njUser.getUserStaffCode());
                messageUserEntity.setPersonId(njUser.getPersonId());
                messageUserEntity.setDeptId(njUser.getDeptId());
                messageUserEntity.setOrgId(njUser.getOrgId());
                messageUserEntity.setUserType(njUser.getUserType());
                messageUserEntity.setCreateDate(njUser.getCreateDate());
                messageUserEntity.setOrd(njUser.getOrd());
                messageUserEntity.setIsAllowLogin(njUser.getIfAllowLogin() != null ? njUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(njUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换南京用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
