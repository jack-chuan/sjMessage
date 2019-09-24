package cn.com.sparknet.sjMessage.datalist.service.impl.nj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.nj.NJDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.nj.NJDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("njDeptService")
public class NJDeptServiceImpl extends ServiceImpl<NJDeptMapper, NJDeptEntity> implements NJDeptService {

    @Autowired
    private NJDeptMapper njDeptMapper;

    @Override
    public List<NJDeptEntity> queryAllList() {
        try {
            return njDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询南京部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<NJDeptEntity> njDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (NJDeptEntity njDept : njDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(njDept.getDeptId());
                messageDeptEntity.setParentDeptId(njDept.getParentDeptId());
                messageDeptEntity.setDeptCode(njDept.getDeptCode());
                messageDeptEntity.setDeptName(njDept.getDeptName());
                messageDeptEntity.setDeptSimpname(njDept.getSimpName());
                messageDeptEntity.setOrgId(njDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(njDept.getCreateDate());
                messageDeptEntity.setOrd(njDept.getOrd());
                messageDeptEntity.setState(njDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换南京部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
