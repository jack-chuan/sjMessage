package cn.com.sparknet.sjMessage.datalist.service.impl.sq;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sq.SQOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.sq.SQOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sqOrgService")
public class SQOrgServiceImpl extends ServiceImpl<SQOrgMapper, SQOrgEntity> implements SQOrgService {

    @Autowired
    private SQOrgMapper sqOrgMapper;

    @Override
    public List<SQOrgEntity> queryAllList() {
        try {
            return sqOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询宿迁组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<SQOrgEntity> sqOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (SQOrgEntity sqOrg : sqOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(sqOrg.getOrgId());
                messageOrgEntity.setParentOrgId(sqOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(sqOrg.getOrgCode());
                messageOrgEntity.setOrgName(sqOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(sqOrg.getSimpName());
                messageOrgEntity.setOrgDblink(sqOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(sqOrg.getLevelName() != null ? Integer.valueOf(sqOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(sqOrg.getCreateDate());
                messageOrgEntity.setOrd(sqOrg.getOrd());
                messageOrgEntity.setState(sqOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换宿迁组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
