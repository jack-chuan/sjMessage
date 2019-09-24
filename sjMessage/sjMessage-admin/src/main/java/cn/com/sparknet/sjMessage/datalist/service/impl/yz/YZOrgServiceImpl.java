package cn.com.sparknet.sjMessage.datalist.service.impl.yz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.yz.YZOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.yz.YZOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("yzOrgService")
public class YZOrgServiceImpl extends ServiceImpl<YZOrgMapper, YZOrgEntity> implements YZOrgService {

    @Autowired
    private YZOrgMapper yzOrgMapper;

    @Override
    public List<YZOrgEntity> queryAllList() {
        try {
            return yzOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询扬州组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<YZOrgEntity> yzOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (YZOrgEntity yzOrg : yzOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(yzOrg.getOrgId());
                messageOrgEntity.setParentOrgId(yzOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(yzOrg.getOrgCode());
                messageOrgEntity.setOrgName(yzOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(yzOrg.getSimpName());
                messageOrgEntity.setOrgDblink(yzOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(yzOrg.getLevelName() != null ? Integer.valueOf(yzOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(yzOrg.getCreateDate());
                messageOrgEntity.setOrd(yzOrg.getOrd());
                messageOrgEntity.setState(yzOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换扬州组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
