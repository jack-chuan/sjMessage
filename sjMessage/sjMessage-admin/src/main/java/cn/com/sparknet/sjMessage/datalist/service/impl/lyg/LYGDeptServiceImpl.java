package cn.com.sparknet.sjMessage.datalist.service.impl.lyg;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.lyg.LYGDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.lyg.LYGDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("lygDeptService")
public class LYGDeptServiceImpl extends ServiceImpl<LYGDeptMapper, LYGDeptEntity> implements LYGDeptService {

    @Autowired
    private LYGDeptMapper lygDeptMapper;

    @Override
    public List<LYGDeptEntity> queryAllList() {
        try {
            return lygDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询连云港部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<LYGDeptEntity> lygDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (LYGDeptEntity lygDept : lygDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(lygDept.getDeptId());
                messageDeptEntity.setParentDeptId(lygDept.getParentDeptId());
                messageDeptEntity.setDeptCode(lygDept.getDeptCode());
                messageDeptEntity.setDeptName(lygDept.getDeptName());
                messageDeptEntity.setDeptSimpname(lygDept.getSimpName());
                messageDeptEntity.setOrgId(lygDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(lygDept.getCreateDate());
                messageDeptEntity.setOrd(lygDept.getOrd());
                messageDeptEntity.setState(lygDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换连云港部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
