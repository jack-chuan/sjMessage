package cn.com.sparknet.sjMessage.datalist.service.impl.cz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.cz.CZOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.cz.CZOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("czOrgService")
public class CZOrgServiceImpl extends ServiceImpl<CZOrgMapper, CZOrgEntity> implements CZOrgService {

    @Autowired
    private CZOrgMapper czOrgMapper;

    @Override
    public List<CZOrgEntity> queryAllList() {
        try {
            return czOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询常州组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<CZOrgEntity> czOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (CZOrgEntity czOrg : czOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(czOrg.getOrgId());
                messageOrgEntity.setParentOrgId(czOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(czOrg.getOrgCode());
                messageOrgEntity.setOrgName(czOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(czOrg.getSimpName());
                messageOrgEntity.setOrgDblink(czOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(czOrg.getLevelName() != null ? Integer.valueOf(czOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(czOrg.getCreateDate());
                messageOrgEntity.setOrd(czOrg.getOrd());
                messageOrgEntity.setState(czOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换常州组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
