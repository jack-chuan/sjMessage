package cn.com.sparknet.sjMessage.datalist.service.impl.cz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.cz.CZPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.cz.CZPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("czPersonService")
public class CZPersonServiceImpl extends ServiceImpl<CZPersonMapper, CZPersonEntity> implements CZPersonService {

    @Autowired
    private CZPersonMapper czPersonMapper;

    @Override
    public List<CZPersonEntity> queryAllList() {
        try {
            return czPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询常州人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<CZPersonEntity> czPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (CZPersonEntity czPerson : czPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(czPerson.getPersonId());
                messagePersonEntity.setPersonName(czPerson.getName());
                messagePersonEntity.setDeptId(czPerson.getDeptId());
                messagePersonEntity.setOrgId(czPerson.getOrgId());
                messagePersonEntity.setPersonDuty(czPerson.getDuty());
                messagePersonEntity.setPersonOfficial(czPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(czPerson.getCreateDate());
                messagePersonEntity.setOrd(czPerson.getOrd());
                messagePersonEntity.setState(czPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换常州人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
