package cn.com.sparknet.sjMessage.datalist.service.impl.nj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.nj.NJOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.nj.NJOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("njOrgService")
public class NJOrgServiceImpl extends ServiceImpl<NJOrgMapper, NJOrgEntity> implements NJOrgService {

    @Autowired
    private NJOrgMapper njOrgMapper;

    @Override
    public List<NJOrgEntity> queryAllList() {
        try {
            return njOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询南京组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<NJOrgEntity> njOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (NJOrgEntity njOrg : njOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(njOrg.getOrgId());
                messageOrgEntity.setParentOrgId(njOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(njOrg.getOrgCode());
                messageOrgEntity.setOrgName(njOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(njOrg.getSimpName());
                messageOrgEntity.setOrgDblink(njOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(njOrg.getLevelName() != null ? Integer.valueOf(njOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(njOrg.getCreateDate());
                messageOrgEntity.setOrd(njOrg.getOrd());
                messageOrgEntity.setState(njOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换南京组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
