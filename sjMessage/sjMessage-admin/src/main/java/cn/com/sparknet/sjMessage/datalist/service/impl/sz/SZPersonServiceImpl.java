package cn.com.sparknet.sjMessage.datalist.service.impl.sz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sz.SZPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.sz.SZPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("szPersonService")
public class SZPersonServiceImpl extends ServiceImpl<SZPersonMapper, SZPersonEntity> implements SZPersonService {

    @Autowired
    private SZPersonMapper szPersonMapper;

    @Override
    public List<SZPersonEntity> queryAllList() {
        try {
            return szPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询苏州人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<SZPersonEntity> szPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (SZPersonEntity szPerson : szPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(szPerson.getPersonId());
                messagePersonEntity.setPersonName(szPerson.getName());
                messagePersonEntity.setDeptId(szPerson.getDeptId());
                messagePersonEntity.setOrgId(szPerson.getOrgId());
                messagePersonEntity.setPersonDuty(szPerson.getDuty());
                messagePersonEntity.setPersonOfficial(szPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(szPerson.getCreateDate());
                messagePersonEntity.setOrd(szPerson.getOrd());
                messagePersonEntity.setState(szPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换苏州人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
