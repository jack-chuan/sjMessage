package cn.com.sparknet.sjMessage.datalist.service.impl.xz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.xz.XZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.xz.XZOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.xz.XZOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("xzOrgService")
public class XZOrgServiceImpl extends ServiceImpl<XZOrgMapper, XZOrgEntity> implements XZOrgService {

    @Autowired
    private XZOrgMapper xzOrgMapper;

    @Override
    public List<XZOrgEntity> queryAllList() {
        try {
            return xzOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询徐州组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<XZOrgEntity> xzOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (XZOrgEntity xzOrg : xzOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(xzOrg.getOrgId());
                messageOrgEntity.setParentOrgId(xzOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(xzOrg.getOrgCode());
                messageOrgEntity.setOrgName(xzOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(xzOrg.getSimpName());
                messageOrgEntity.setOrgDblink(xzOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(xzOrg.getLevelName() != null ? Integer.valueOf(xzOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(xzOrg.getCreateDate());
                messageOrgEntity.setOrd(xzOrg.getOrd());
                messageOrgEntity.setState(xzOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换徐州组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
