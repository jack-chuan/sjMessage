package cn.com.sparknet.sjMessage.datalist.service.impl.wx;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.wx.WXDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.wx.WXDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.wx.WXDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("wxDeptService")
public class WXDeptServiceImpl extends ServiceImpl<WXDeptMapper, WXDeptEntity> implements WXDeptService {

    @Autowired
    private WXDeptMapper wxDeptMapper;

    @Override
    public List<WXDeptEntity> queryAllList() {
        try {
            return wxDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询无锡部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<WXDeptEntity> wxDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (WXDeptEntity wxDept : wxDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(wxDept.getDeptId());
                messageDeptEntity.setParentDeptId(wxDept.getParentDeptId());
                messageDeptEntity.setDeptCode(wxDept.getDeptCode());
                messageDeptEntity.setDeptName(wxDept.getDeptName());
                messageDeptEntity.setDeptSimpname(wxDept.getSimpName());
                messageDeptEntity.setOrgId(wxDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(wxDept.getCreateDate());
                messageDeptEntity.setOrd(wxDept.getOrd());
                messageDeptEntity.setState(wxDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换无锡部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
