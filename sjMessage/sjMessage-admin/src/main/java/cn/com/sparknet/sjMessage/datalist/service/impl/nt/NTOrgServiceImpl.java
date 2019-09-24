package cn.com.sparknet.sjMessage.datalist.service.impl.nt;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.nt.NTOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.nt.NTOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("ntOrgService")
public class NTOrgServiceImpl extends ServiceImpl<NTOrgMapper, NTOrgEntity> implements NTOrgService {

    @Autowired
    private NTOrgMapper ntOrgMapper;

    @Override
    public List<NTOrgEntity> queryAllList() {
        try {
            return ntOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询南通组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<NTOrgEntity> ntOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (NTOrgEntity ntOrg : ntOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(ntOrg.getOrgId());
                messageOrgEntity.setParentOrgId(ntOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(ntOrg.getOrgCode());
                messageOrgEntity.setOrgName(ntOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(ntOrg.getSimpName());
                messageOrgEntity.setOrgDblink(ntOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(ntOrg.getLevelName() != null ? Integer.valueOf(ntOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(ntOrg.getCreateDate());
                messageOrgEntity.setOrd(ntOrg.getOrd());
                messageOrgEntity.setState(ntOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换南通组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
