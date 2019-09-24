package cn.com.sparknet.sjMessage.datalist.service.impl.lyg;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.lyg.LYGOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.lyg.LYGOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("lygOrgService")
public class LYGOrgServiceImpl extends ServiceImpl<LYGOrgMapper, LYGOrgEntity> implements LYGOrgService {

    @Autowired
    private LYGOrgMapper lygOrgMapper;

    @Override
    public List<LYGOrgEntity> queryAllList() {
        try {
            return lygOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询连云港组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<LYGOrgEntity> lygOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (LYGOrgEntity lygOrg : lygOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(lygOrg.getOrgId());
                messageOrgEntity.setParentOrgId(lygOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(lygOrg.getOrgCode());
                messageOrgEntity.setOrgName(lygOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(lygOrg.getSimpName());
                messageOrgEntity.setOrgDblink(lygOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(lygOrg.getLevelName() != null ? Integer.valueOf(lygOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(lygOrg.getCreateDate());
                messageOrgEntity.setOrd(lygOrg.getOrd());
                messageOrgEntity.setState(lygOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换连云港组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
