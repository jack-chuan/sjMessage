package cn.com.sparknet.sjMessage.datalist.service.impl.zj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.zj.ZJDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.zj.ZJDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("zjDeptService")
public class ZJDeptServiceImpl extends ServiceImpl<ZJDeptMapper, ZJDeptEntity> implements ZJDeptService {

    @Autowired
    private ZJDeptMapper zjDeptMapper;

    @Override
    public List<ZJDeptEntity> queryAllList() {
        try {
            return zjDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询镇江部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<ZJDeptEntity> zjDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (ZJDeptEntity zjDept : zjDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(zjDept.getDeptId());
                messageDeptEntity.setParentDeptId(zjDept.getParentDeptId());
                messageDeptEntity.setDeptCode(zjDept.getDeptCode());
                messageDeptEntity.setDeptName(zjDept.getDeptName());
                messageDeptEntity.setDeptSimpname(zjDept.getSimpName());
                messageDeptEntity.setOrgId(zjDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(zjDept.getCreateDate());
                messageDeptEntity.setOrd(zjDept.getOrd());
                messageDeptEntity.setState(zjDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换镇江部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
