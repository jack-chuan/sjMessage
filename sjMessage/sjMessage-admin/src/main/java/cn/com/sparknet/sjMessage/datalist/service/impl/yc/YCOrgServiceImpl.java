package cn.com.sparknet.sjMessage.datalist.service.impl.yc;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yc.YCOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.yc.YCOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.yc.YCOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("ycOrgService")
public class YCOrgServiceImpl extends ServiceImpl<YCOrgMapper, YCOrgEntity> implements YCOrgService {

    @Autowired
    private YCOrgMapper ycOrgMapper;

    @Override
    public List<YCOrgEntity> queryAllList() {
        try {
            return ycOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询盐城组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> changeList(List<YCOrgEntity> ycOrgEntityList) {
        List<MsOrgEntity> messageOrgEntityList = new ArrayList<MsOrgEntity>();
        MsOrgEntity messageOrgEntity = null;
        try {
            for (YCOrgEntity ycOrg : ycOrgEntityList){
                messageOrgEntity = new MsOrgEntity();
                messageOrgEntity.setOrgId(ycOrg.getOrgId());
                messageOrgEntity.setParentOrgId(ycOrg.getParentOrgId());
                messageOrgEntity.setOrgCode(ycOrg.getOrgCode());
                messageOrgEntity.setOrgName(ycOrg.getOrgName());
                messageOrgEntity.setOrgSimpname(ycOrg.getSimpName());
                messageOrgEntity.setOrgDblink(ycOrg.getDbLinkName());
                messageOrgEntity.setOrgLevel(ycOrg.getLevelName() != null ? Integer.valueOf(ycOrg.getLevelName()) : null);
                messageOrgEntity.setCreateDate(ycOrg.getCreateDate());
                messageOrgEntity.setOrd(ycOrg.getOrd());
                messageOrgEntity.setState(ycOrg.getState());
                messageOrgEntityList.add(messageOrgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换盐城组织为消息组织失败");
        }
        return messageOrgEntityList;
    }
}
