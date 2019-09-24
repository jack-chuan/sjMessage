package cn.com.sparknet.sjMessage.datalist.service.impl.nj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.nj.NJPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.nj.NJPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("njPersonService")
public class NJPersonServiceImpl extends ServiceImpl<NJPersonMapper, NJPersonEntity> implements NJPersonService {

    @Autowired
    private NJPersonMapper njPersonMapper;

    @Override
    public List<NJPersonEntity> queryAllList() {
        try {
            return njPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询南京人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<NJPersonEntity> njPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (NJPersonEntity njPerson : njPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(njPerson.getPersonId());
                messagePersonEntity.setPersonName(njPerson.getName());
                messagePersonEntity.setDeptId(njPerson.getDeptId());
                messagePersonEntity.setOrgId(njPerson.getOrgId());
                messagePersonEntity.setPersonDuty(njPerson.getDuty());
                messagePersonEntity.setPersonOfficial(njPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(njPerson.getCreateDate());
                messagePersonEntity.setOrd(njPerson.getOrd());
                messagePersonEntity.setState(njPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换南京人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
