package cn.com.sparknet.sjMessage.datalist.service.impl.wx;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.wx.WXUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.wx.WXUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.wx.WXUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("wxUserService")
public class WXUserServiceImpl extends ServiceImpl<WXUserMapper, WXUserEntity> implements WXUserService {

    @Autowired
    private WXUserMapper wxUserMapper;

    @Override
    public List<WXUserEntity> queryAllList() {
        try {
            return wxUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询无锡用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> changeList(List<WXUserEntity> wxUserEntityList) {
        List<MsUserEntity> messageUserEntityList = new ArrayList<MsUserEntity>();
        MsUserEntity messageUserEntity = null;
        try {
            for (WXUserEntity wxUser : wxUserEntityList){
                messageUserEntity = new MsUserEntity();
                messageUserEntity.setUserId(wxUser.getUserId());
                messageUserEntity.setUsername(wxUser.getUserStaffCode());
                messageUserEntity.setPersonId(wxUser.getPersonId());
                messageUserEntity.setDeptId(wxUser.getDeptId());
                messageUserEntity.setOrgId(wxUser.getOrgId());
                messageUserEntity.setUserType(wxUser.getUserType());
                messageUserEntity.setCreateDate(wxUser.getCreateDate());
                messageUserEntity.setOrd(wxUser.getOrd());
                messageUserEntity.setIsAllowLogin(wxUser.getIfAllowLogin() != null ? wxUser.getIfAllowLogin().toString() : null);
                messageUserEntity.setState(wxUser.getState());
                messageUserEntityList.add(messageUserEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换无锡用户为消息用户失败");
        }
        return messageUserEntityList;
    }

}
