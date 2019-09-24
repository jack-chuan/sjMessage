package cn.com.sparknet.sjMessage.datalist.service.impl.nt;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.nt.NTPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.nt.NTPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("ntPersonService")
public class NTPersonServiceImpl extends ServiceImpl<NTPersonMapper, NTPersonEntity> implements NTPersonService {

    @Autowired
    private NTPersonMapper ntPersonMapper;

    @Override
    public List<NTPersonEntity> queryAllList() {
        try {
            return ntPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询南通人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<NTPersonEntity> ntPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (NTPersonEntity ntPerson : ntPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(ntPerson.getPersonId());
                messagePersonEntity.setPersonName(ntPerson.getName());
                messagePersonEntity.setDeptId(ntPerson.getDeptId());
                messagePersonEntity.setOrgId(ntPerson.getOrgId());
                messagePersonEntity.setPersonDuty(ntPerson.getDuty());
                messagePersonEntity.setPersonOfficial(ntPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(ntPerson.getCreateDate());
                messagePersonEntity.setOrd(ntPerson.getOrd());
                messagePersonEntity.setState(ntPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换南通人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
