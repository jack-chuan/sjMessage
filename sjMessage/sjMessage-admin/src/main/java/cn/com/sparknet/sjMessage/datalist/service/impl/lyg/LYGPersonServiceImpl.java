package cn.com.sparknet.sjMessage.datalist.service.impl.lyg;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.lyg.LYGPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.lyg.LYGPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("lygPersonService")
public class LYGPersonServiceImpl extends ServiceImpl<LYGPersonMapper, LYGPersonEntity> implements LYGPersonService {

    @Autowired
    private LYGPersonMapper lygPersonMapper;

    @Override
    public List<LYGPersonEntity> queryAllList() {
        try {
            return lygPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询连云港人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<LYGPersonEntity> lygPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (LYGPersonEntity lygPerson : lygPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(lygPerson.getPersonId());
                messagePersonEntity.setPersonName(lygPerson.getName());
                messagePersonEntity.setDeptId(lygPerson.getDeptId());
                messagePersonEntity.setOrgId(lygPerson.getOrgId());
                messagePersonEntity.setPersonDuty(lygPerson.getDuty());
                messagePersonEntity.setPersonOfficial(lygPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(lygPerson.getCreateDate());
                messagePersonEntity.setOrd(lygPerson.getOrd());
                messagePersonEntity.setState(lygPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换连云港人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
