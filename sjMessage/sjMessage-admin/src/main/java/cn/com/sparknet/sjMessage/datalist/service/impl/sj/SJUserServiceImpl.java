package cn.com.sparknet.sjMessage.datalist.service.impl.sj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sj.SJUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.sj.SJUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sjUserService")
public class SJUserServiceImpl extends ServiceImpl<SJUserMapper, SJUserEntity> implements SJUserService {

    @Autowired
    private SJUserMapper sjUserMapper;

    @Override
    public List<SJUserEntity> queryAllList() {
        try {
            return sjUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询省局用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<SJUserEntity> sjUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (SJUserEntity sjUser : sjUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(sjUser.getUserId());
                messageUserEntity.setUsername(sjUser.getUserStaffCode());
                messageUserEntity.setPersonId(sjUser.getPersonId());
                messageUserEntity.setDeptId(sjUser.getDeptId());
                messageUserEntity.setOrgId(sjUser.getOrgId());
                messageUserEntity.setUserType(sjUser.getUserType());
                messageUserEntity.setCreateDate(sjUser.getCreateDate());
                messageUserEntity.setOrd(sjUser.getOrd());
                messageUserEntity.setIsAllowLogin(sjUser.getIfAllowLogin() != null ? sjUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(sjUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换省局用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
