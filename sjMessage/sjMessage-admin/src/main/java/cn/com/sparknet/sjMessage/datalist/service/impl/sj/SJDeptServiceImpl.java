package cn.com.sparknet.sjMessage.datalist.service.impl.sj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sj.SJDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.sj.SJDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sjDeptService")
public class SJDeptServiceImpl extends ServiceImpl<SJDeptMapper, SJDeptEntity> implements SJDeptService {

    @Autowired
    private SJDeptMapper sjDeptMapper;

    @Override
    public List<SJDeptEntity> queryAllList() {
        try {
            return sjDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询省局部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<SJDeptEntity> sjDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (SJDeptEntity sjDept : sjDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(sjDept.getDeptId());
                messageDeptEntity.setParentDeptId(sjDept.getParentDeptId());
                messageDeptEntity.setDeptCode(sjDept.getDeptCode());
                messageDeptEntity.setDeptName(sjDept.getDeptName());
                messageDeptEntity.setDeptSimpname(sjDept.getSimpName());
                messageDeptEntity.setOrgId(sjDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(sjDept.getCreateDate());
                messageDeptEntity.setOrd(sjDept.getOrd());
                messageDeptEntity.setState(sjDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换省局部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
