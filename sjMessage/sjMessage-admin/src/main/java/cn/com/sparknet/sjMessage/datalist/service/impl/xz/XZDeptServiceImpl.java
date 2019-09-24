package cn.com.sparknet.sjMessage.datalist.service.impl.xz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.xz.XZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.xz.XZDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.xz.XZDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("xzDeptService")
public class XZDeptServiceImpl extends ServiceImpl<XZDeptMapper, XZDeptEntity> implements XZDeptService {

    @Autowired
    private XZDeptMapper xzDeptMapper;

    @Override
    public List<XZDeptEntity> queryAllList() {
        try {
            return xzDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询徐州部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<XZDeptEntity> xzDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (XZDeptEntity xzDept : xzDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(xzDept.getDeptId());
                messageDeptEntity.setParentDeptId(xzDept.getParentDeptId());
                messageDeptEntity.setDeptCode(xzDept.getDeptCode());
                messageDeptEntity.setDeptName(xzDept.getDeptName());
                messageDeptEntity.setDeptSimpname(xzDept.getSimpName());
                messageDeptEntity.setOrgId(xzDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(xzDept.getCreateDate());
                messageDeptEntity.setOrd(xzDept.getOrd());
                messageDeptEntity.setState(xzDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换徐州部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
