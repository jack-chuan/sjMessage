package cn.com.sparknet.sjMessage.datalist.service.impl.sz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sz.SZDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.sz.SZDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("szDeptService")
public class SZDeptServiceImpl extends ServiceImpl<SZDeptMapper, SZDeptEntity> implements SZDeptService {

    @Autowired
    private SZDeptMapper szDeptMapper;

    @Override
    public List<SZDeptEntity> queryAllList() {
        try {
            return szDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询苏州部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<SZDeptEntity> szDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (SZDeptEntity szDept : szDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(szDept.getDeptId());
                messageDeptEntity.setParentDeptId(szDept.getParentDeptId());
                messageDeptEntity.setDeptCode(szDept.getDeptCode());
                messageDeptEntity.setDeptName(szDept.getDeptName());
                messageDeptEntity.setDeptSimpname(szDept.getSimpName());
                messageDeptEntity.setOrgId(szDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(szDept.getCreateDate());
                messageDeptEntity.setOrd(szDept.getOrd());
                messageDeptEntity.setState(szDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换苏州部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
