package cn.com.sparknet.sjMessage.datalist.service.impl.nt;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.nt.NTUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.nt.NTUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("ntUserService")
public class NTUserServiceImpl extends ServiceImpl<NTUserMapper, NTUserEntity> implements NTUserService {

    @Autowired
    private NTUserMapper ntUserMapper;

    @Override
    public List<NTUserEntity> queryAllList() {
        try {
            return ntUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询南通用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<NTUserEntity> ntUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (NTUserEntity ntUser : ntUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(ntUser.getUserId());
                messageUserEntity.setUsername(ntUser.getUserStaffCode());
                messageUserEntity.setPersonId(ntUser.getPersonId());
                messageUserEntity.setDeptId(ntUser.getDeptId());
                messageUserEntity.setOrgId(ntUser.getOrgId());
                messageUserEntity.setUserType(ntUser.getUserType());
                messageUserEntity.setCreateDate(ntUser.getCreateDate());
                messageUserEntity.setOrd(ntUser.getOrd());
                messageUserEntity.setIsAllowLogin(ntUser.getIfAllowLogin() != null ? ntUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(ntUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换南通用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
