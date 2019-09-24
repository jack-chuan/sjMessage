package cn.com.sparknet.sjMessage.datalist.service.impl.sj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sj.SJOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.sj.SJOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sjOrgService")
public class SJOrgServiceImpl extends ServiceImpl<SJOrgMapper, SJOrgEntity> implements SJOrgService {

    @Autowired
    private SJOrgMapper sjOrgMapper;

    @Override
    public List<SJOrgEntity> queryAllList(List<String> cityORGID) {
        try {
            return sjOrgMapper.queryAllList(cityORGID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询省局组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<SJOrgEntity> sjOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (SJOrgEntity sjOrg : sjOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(sjOrg.getOrgId());
                messageOrgEntity.setParentOrgId(sjOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(sjOrg.getOrgCode());
                messageOrgEntity.setOrgName(sjOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(sjOrg.getSimpName());
                messageOrgEntity.setOrgDblink(sjOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(sjOrg.getLevelName() != null ? Integer.valueOf(sjOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(sjOrg.getCreateDate());
                messageOrgEntity.setOrd(sjOrg.getOrd());
                messageOrgEntity.setState(sjOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换省局组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
