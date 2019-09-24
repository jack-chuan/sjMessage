package cn.com.sparknet.sjMessage.datalist.service.impl.sj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sj.SJPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.sj.SJPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sjPersonService")
public class SJPersonServiceImpl extends ServiceImpl<SJPersonMapper, SJPersonEntity> implements SJPersonService {

    @Autowired
    private SJPersonMapper sjPersonMapper;

    @Override
    public List<SJPersonEntity> queryAllList() {
        try {
            return sjPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询省局人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<SJPersonEntity> sjPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (SJPersonEntity sjPerson : sjPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(sjPerson.getPersonId());
                messagePersonEntity.setPersonName(sjPerson.getName());
                messagePersonEntity.setDeptId(sjPerson.getDeptId());
                messagePersonEntity.setOrgId(sjPerson.getOrgId());
                messagePersonEntity.setPersonDuty(sjPerson.getDuty());
                messagePersonEntity.setPersonOfficial(sjPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(sjPerson.getCreateDate());
                messagePersonEntity.setOrd(sjPerson.getOrd());
                messagePersonEntity.setState(sjPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换省局人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
