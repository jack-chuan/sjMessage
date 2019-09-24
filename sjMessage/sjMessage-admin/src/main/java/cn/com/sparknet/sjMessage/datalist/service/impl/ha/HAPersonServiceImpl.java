package cn.com.sparknet.sjMessage.datalist.service.impl.ha;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.ha.HAPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.ha.HAPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.ha.HAPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("haPersonService")
public class HAPersonServiceImpl extends ServiceImpl<HAPersonMapper, HAPersonEntity> implements HAPersonService {

    @Autowired
    private HAPersonMapper haPersonMapper;

    @Override
    public List<HAPersonEntity> queryAllList() {
        try {
            return haPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询淮安人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<HAPersonEntity> haPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (HAPersonEntity haPerson : haPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(haPerson.getPersonId());
                messagePersonEntity.setPersonName(haPerson.getName());
                messagePersonEntity.setDeptId(haPerson.getDeptId());
                messagePersonEntity.setOrgId(haPerson.getOrgId());
                messagePersonEntity.setPersonDuty(haPerson.getDuty());
                messagePersonEntity.setPersonOfficial(haPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(haPerson.getCreateDate());
                messagePersonEntity.setOrd(haPerson.getOrd());
                messagePersonEntity.setState(haPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换淮安人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
