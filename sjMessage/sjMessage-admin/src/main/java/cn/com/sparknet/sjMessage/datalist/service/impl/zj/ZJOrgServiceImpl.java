package cn.com.sparknet.sjMessage.datalist.service.impl.zj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.zj.ZJOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.zj.ZJOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("zjOrgService")
public class ZJOrgServiceImpl extends ServiceImpl<ZJOrgMapper, ZJOrgEntity> implements ZJOrgService {

    @Autowired
    private ZJOrgMapper zjOrgMapper;

    @Override
    public List<ZJOrgEntity> queryAllList() {
        try {
            return zjOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询镇江组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<ZJOrgEntity> zjOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (ZJOrgEntity zjOrg : zjOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(zjOrg.getOrgId());
                messageOrgEntity.setParentOrgId(zjOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(zjOrg.getOrgCode());
                messageOrgEntity.setOrgName(zjOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(zjOrg.getSimpName());
                messageOrgEntity.setOrgDblink(zjOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(zjOrg.getLevelName() != null ? Integer.valueOf(zjOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(zjOrg.getCreateDate());
                messageOrgEntity.setOrd(zjOrg.getOrd());
                messageOrgEntity.setState(zjOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换镇江组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
