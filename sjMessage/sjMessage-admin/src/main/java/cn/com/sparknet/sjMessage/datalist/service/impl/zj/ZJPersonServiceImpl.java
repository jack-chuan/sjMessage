package cn.com.sparknet.sjMessage.datalist.service.impl.zj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.zj.ZJPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.zj.ZJPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("zjPersonService")
public class ZJPersonServiceImpl extends ServiceImpl<ZJPersonMapper, ZJPersonEntity> implements ZJPersonService {

    @Autowired
    private ZJPersonMapper zjPersonMapper;

    @Override
    public List<ZJPersonEntity> queryAllList() {
        try {
            return zjPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询镇江人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<ZJPersonEntity> zjPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (ZJPersonEntity zjPerson : zjPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(zjPerson.getPersonId());
                messagePersonEntity.setPersonName(zjPerson.getName());
                messagePersonEntity.setDeptId(zjPerson.getDeptId());
                messagePersonEntity.setOrgId(zjPerson.getOrgId());
                messagePersonEntity.setPersonDuty(zjPerson.getDuty());
                messagePersonEntity.setPersonOfficial(zjPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(zjPerson.getCreateDate());
                messagePersonEntity.setOrd(zjPerson.getOrd());
                messagePersonEntity.setState(zjPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换镇江人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
