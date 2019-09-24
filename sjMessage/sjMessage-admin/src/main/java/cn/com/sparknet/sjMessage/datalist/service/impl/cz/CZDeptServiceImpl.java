package cn.com.sparknet.sjMessage.datalist.service.impl.cz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.cz.CZDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.cz.CZDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("czDeptService")
public class CZDeptServiceImpl extends ServiceImpl<CZDeptMapper, CZDeptEntity> implements CZDeptService {

    @Autowired
    private CZDeptMapper czDeptMapper;

    @Override
    public List<CZDeptEntity> queryAllList() {
        try {
            return czDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询常州部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<CZDeptEntity> czDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (CZDeptEntity czDept : czDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(czDept.getDeptId());
                messageDeptEntity.setParentDeptId(czDept.getParentDeptId());
                messageDeptEntity.setDeptCode(czDept.getDeptCode());
                messageDeptEntity.setDeptName(czDept.getDeptName());
                messageDeptEntity.setDeptSimpname(czDept.getSimpName());
                messageDeptEntity.setOrgId(czDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(czDept.getCreateDate());
                messageDeptEntity.setOrd(czDept.getOrd());
                messageDeptEntity.setState(czDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换常州部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
