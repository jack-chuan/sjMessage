package cn.com.sparknet.sjMessage.datalist.service.impl.nt;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.nt.NTDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.nt.NTDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("ntDeptService")
public class NTDeptServiceImpl extends ServiceImpl<NTDeptMapper, NTDeptEntity> implements NTDeptService {

    @Autowired
    private NTDeptMapper ntDeptMapper;

    @Override
    public List<NTDeptEntity> queryAllList() {
        try {
            return ntDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询南通部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<NTDeptEntity> ntDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (NTDeptEntity ntDept : ntDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(ntDept.getDeptId());
                messageDeptEntity.setParentDeptId(ntDept.getParentDeptId());
                messageDeptEntity.setDeptCode(ntDept.getDeptCode());
                messageDeptEntity.setDeptName(ntDept.getDeptName());
                messageDeptEntity.setDeptSimpname(ntDept.getSimpName());
                messageDeptEntity.setOrgId(ntDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(ntDept.getCreateDate());
                messageDeptEntity.setOrd(ntDept.getOrd());
                messageDeptEntity.setState(ntDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换南通部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
