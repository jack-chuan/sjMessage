package cn.com.sparknet.sjMessage.datalist.service.impl.yz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.yz.YZPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.yz.YZPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("yzPersonService")
public class YZPersonServiceImpl extends ServiceImpl<YZPersonMapper, YZPersonEntity> implements YZPersonService {

    @Autowired
    private YZPersonMapper yzPersonMapper;

    @Override
    public List<YZPersonEntity> queryAllList() {
        try {
            return yzPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询扬州人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<YZPersonEntity> yzPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (YZPersonEntity yzPerson : yzPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(yzPerson.getPersonId());
                messagePersonEntity.setPersonName(yzPerson.getName());
                messagePersonEntity.setDeptId(yzPerson.getDeptId());
                messagePersonEntity.setOrgId(yzPerson.getOrgId());
                messagePersonEntity.setPersonDuty(yzPerson.getDuty());
                messagePersonEntity.setPersonOfficial(yzPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(yzPerson.getCreateDate());
                messagePersonEntity.setOrd(yzPerson.getOrd());
                messagePersonEntity.setState(yzPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换扬州人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
