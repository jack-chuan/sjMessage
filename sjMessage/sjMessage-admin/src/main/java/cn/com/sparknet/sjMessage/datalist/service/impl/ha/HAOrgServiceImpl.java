package cn.com.sparknet.sjMessage.datalist.service.impl.ha;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.ha.HAOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.ha.HAOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.ha.HAOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("haOrgService")
public class HAOrgServiceImpl extends ServiceImpl<HAOrgMapper, HAOrgEntity> implements HAOrgService {

    @Autowired
    private HAOrgMapper haOrgMapper;

    @Override
    public List<HAOrgEntity> queryAllList() {
        try {
            return haOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询淮安组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<HAOrgEntity> haOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (HAOrgEntity haOrg : haOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(haOrg.getOrgId());
                messageOrgEntity.setParentOrgId(haOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(haOrg.getOrgCode());
                messageOrgEntity.setOrgName(haOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(haOrg.getSimpName());
                messageOrgEntity.setOrgDblink(haOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(haOrg.getLevelName() != null ? Integer.valueOf(haOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(haOrg.getCreateDate());
                messageOrgEntity.setOrd(haOrg.getOrd());
                messageOrgEntity.setState(haOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换淮安组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
