package cn.com.sparknet.sjMessage.datalist.service.impl.tz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.tz.TZPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.tz.TZPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("tzPersonService")
public class TZPersonServiceImpl extends ServiceImpl<TZPersonMapper, TZPersonEntity> implements TZPersonService {

    @Autowired
    private TZPersonMapper tzPersonMapper;

    @Override
    public List<TZPersonEntity> queryAllList() {
        try {
            return tzPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询泰州人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<TZPersonEntity> tzPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (TZPersonEntity tzPerson : tzPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(tzPerson.getPersonId());
                messagePersonEntity.setPersonName(tzPerson.getName());
                messagePersonEntity.setDeptId(tzPerson.getDeptId());
                messagePersonEntity.setOrgId(tzPerson.getOrgId());
                messagePersonEntity.setPersonDuty(tzPerson.getDuty());
                messagePersonEntity.setPersonOfficial(tzPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(tzPerson.getCreateDate());
                messagePersonEntity.setOrd(tzPerson.getOrd());
                messagePersonEntity.setState(tzPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换泰州人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
