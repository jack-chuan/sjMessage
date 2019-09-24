package cn.com.sparknet.sjMessage.datalist.service.impl.wx;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.wx.WXOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.wx.WXOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.wx.WXOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("wxOrgService")
public class WXOrgServiceImpl extends ServiceImpl<WXOrgMapper, WXOrgEntity> implements WXOrgService {

    @Autowired
    private WXOrgMapper wxOrgMapper;

    @Override
    public List<WXOrgEntity> queryAllList() {
        try {
            return wxOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询无锡组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<WXOrgEntity> wxOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (WXOrgEntity wxOrg : wxOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(wxOrg.getOrgId());
                messageOrgEntity.setParentOrgId(wxOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(wxOrg.getOrgCode());
                messageOrgEntity.setOrgName(wxOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(wxOrg.getSimpName());
                messageOrgEntity.setOrgDblink(wxOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(wxOrg.getLevelName() != null ? Integer.valueOf(wxOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(wxOrg.getCreateDate());
                messageOrgEntity.setOrd(wxOrg.getOrd());
                messageOrgEntity.setState(wxOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换无锡组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
