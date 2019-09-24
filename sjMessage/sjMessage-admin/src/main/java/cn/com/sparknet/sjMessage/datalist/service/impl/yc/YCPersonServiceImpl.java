package cn.com.sparknet.sjMessage.datalist.service.impl.yc;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yc.YCPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.yc.YCPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.yc.YCPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("ycPersonService")
public class YCPersonServiceImpl extends ServiceImpl<YCPersonMapper, YCPersonEntity> implements YCPersonService {

    @Autowired
    private YCPersonMapper ycPersonMapper;

    @Override
    public List<YCPersonEntity> queryAllList() {
        try {
            return ycPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询盐城人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<YCPersonEntity> ycPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (YCPersonEntity ycPerson : ycPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(ycPerson.getPersonId());
                messagePersonEntity.setPersonName(ycPerson.getName());
                messagePersonEntity.setDeptId(ycPerson.getDeptId());
                messagePersonEntity.setOrgId(ycPerson.getOrgId());
                messagePersonEntity.setPersonDuty(ycPerson.getDuty());
                messagePersonEntity.setPersonOfficial(ycPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(ycPerson.getCreateDate());
                messagePersonEntity.setOrd(ycPerson.getOrd());
                messagePersonEntity.setState(ycPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换盐城人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
