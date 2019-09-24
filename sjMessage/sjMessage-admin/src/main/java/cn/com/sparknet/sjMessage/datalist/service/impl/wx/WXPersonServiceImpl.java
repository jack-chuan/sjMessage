package cn.com.sparknet.sjMessage.datalist.service.impl.wx;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.wx.WXPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.wx.WXPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.wx.WXPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("wxPersonService")
public class WXPersonServiceImpl extends ServiceImpl<WXPersonMapper, WXPersonEntity> implements WXPersonService {

    @Autowired
    private WXPersonMapper wxPersonMapper;

    @Override
    public List<WXPersonEntity> queryAllList() {
        try {
            return wxPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询无锡人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> changeList(List<WXPersonEntity> wxPersonEntityList) {
        List<MsPersonEntity> messagePersonEntityList = new ArrayList<MsPersonEntity>();
        MsPersonEntity messagePersonEntity = null;
        try {
            for (WXPersonEntity wxPerson : wxPersonEntityList){
                messagePersonEntity = new MsPersonEntity();
                messagePersonEntity.setPersonId(wxPerson.getPersonId());
                messagePersonEntity.setPersonName(wxPerson.getName());
                messagePersonEntity.setDeptId(wxPerson.getDeptId());
                messagePersonEntity.setOrgId(wxPerson.getOrgId());
                messagePersonEntity.setPersonDuty(wxPerson.getDuty());
                messagePersonEntity.setPersonOfficial(wxPerson.getRoomnumber());
                messagePersonEntity.setCreateDate(wxPerson.getCreateDate());
                messagePersonEntity.setOrd(wxPerson.getOrd());
                messagePersonEntity.setState(wxPerson.getState());
                messagePersonEntityList.add(messagePersonEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换无锡人员为消息人员失败");
        }
        return messagePersonEntityList;
    }

}
