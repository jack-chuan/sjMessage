package cn.com.sparknet.sjMessage.datalist.service.impl.sz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sz.SZOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.sz.SZOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("szOrgService")
public class SZOrgServiceImpl extends ServiceImpl<SZOrgMapper, SZOrgEntity> implements SZOrgService {

    @Autowired
    private SZOrgMapper szOrgMapper;

    @Override
    public List<SZOrgEntity> queryAllList() {
        try {
            return szOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询苏州组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<SZOrgEntity> szOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (SZOrgEntity szOrg : szOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(szOrg.getOrgId());
                messageOrgEntity.setParentOrgId(szOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(szOrg.getOrgCode());
                messageOrgEntity.setOrgName(szOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(szOrg.getSimpName());
                messageOrgEntity.setOrgDblink(szOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(szOrg.getLevelName() != null ? Integer.valueOf(szOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(szOrg.getCreateDate());
                messageOrgEntity.setOrd(szOrg.getOrd());
                messageOrgEntity.setState(szOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换苏州组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
