package cn.com.sparknet.sjMessage.datalist.service.impl.xz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.xz.XZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.xz.XZPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.xz.XZPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("xzPersonService")
public class XZPersonServiceImpl extends ServiceImpl<XZPersonMapper, XZPersonEntity> implements XZPersonService {

    @Autowired
    private XZPersonMapper xzPersonMapper;

    @Override
    public List<XZPersonEntity> queryAllList() {
        try {
            return xzPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询徐州人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<XZPersonEntity> xzPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (XZPersonEntity xzPerson : xzPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(xzPerson.getPersonId());
                messagePersonEntity.setPersonName(xzPerson.getName());
                messagePersonEntity.setDeptId(xzPerson.getDeptId());
                messagePersonEntity.setOrgId(xzPerson.getOrgId());
                messagePersonEntity.setPersonDuty(xzPerson.getDuty());
                messagePersonEntity.setPersonOfficial(xzPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(xzPerson.getCreateDate());
                messagePersonEntity.setOrd(xzPerson.getOrd());
                messagePersonEntity.setState(xzPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换徐州人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
