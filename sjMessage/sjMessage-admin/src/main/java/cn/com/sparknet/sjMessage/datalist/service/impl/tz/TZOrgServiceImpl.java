package cn.com.sparknet.sjMessage.datalist.service.impl.tz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.tz.TZOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.tz.TZOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("tzOrgService")
public class TZOrgServiceImpl extends ServiceImpl<TZOrgMapper, TZOrgEntity> implements TZOrgService {

    @Autowired
    private TZOrgMapper tzOrgMapper;

    @Override
    public List<TZOrgEntity> queryAllList() {
        try {
            return tzOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询泰州组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<TZOrgEntity> tzOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (TZOrgEntity tzOrg : tzOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(tzOrg.getOrgId());
                messageOrgEntity.setParentOrgId(tzOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(tzOrg.getOrgCode());
                messageOrgEntity.setOrgName(tzOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(tzOrg.getSimpName());
                messageOrgEntity.setOrgDblink(tzOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(tzOrg.getLevelName() != null ? Integer.valueOf(tzOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(tzOrg.getCreateDate());
                messageOrgEntity.setOrd(tzOrg.getOrd());
                messageOrgEntity.setState(tzOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换泰州组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
