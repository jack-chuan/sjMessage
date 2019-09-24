package cn.com.sparknet.sjMessage.datalist.service.impl.sq;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sq.SQPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.sq.SQPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sqPersonService")
public class SQPersonServiceImpl extends ServiceImpl<SQPersonMapper, SQPersonEntity> implements SQPersonService {

    @Autowired
    private SQPersonMapper sqPersonMapper;

    @Override
    public List<SQPersonEntity> queryAllList() {
        try {
            return sqPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询宿迁人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<SQPersonEntity> sqPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (SQPersonEntity sqPerson : sqPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(sqPerson.getPersonId());
                messagePersonEntity.setPersonName(sqPerson.getName());
                messagePersonEntity.setDeptId(sqPerson.getDeptId());
                messagePersonEntity.setOrgId(sqPerson.getOrgId());
                messagePersonEntity.setPersonDuty(sqPerson.getDuty());
                messagePersonEntity.setPersonOfficial(sqPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(sqPerson.getCreateDate());
                messagePersonEntity.setOrd(sqPerson.getOrd());
                messagePersonEntity.setState(sqPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换宿迁人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
