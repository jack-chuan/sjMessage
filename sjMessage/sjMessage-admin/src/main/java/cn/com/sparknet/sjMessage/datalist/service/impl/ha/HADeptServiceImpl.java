package cn.com.sparknet.sjMessage.datalist.service.impl.ha;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.ha.HADeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.ha.HADeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.ha.HADeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("haDeptService")
public class HADeptServiceImpl extends ServiceImpl<HADeptMapper, HADeptEntity> implements HADeptService {

    @Autowired
    private HADeptMapper haDeptMapper;

    @Override
    public List<HADeptEntity> queryAllList() {
        try {
            return haDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询淮安部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<HADeptEntity> haDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (HADeptEntity haDept : haDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(haDept.getDeptId());
                messageDeptEntity.setParentDeptId(haDept.getParentDeptId());
                messageDeptEntity.setDeptCode(haDept.getDeptCode());
                messageDeptEntity.setDeptName(haDept.getDeptName());
                messageDeptEntity.setDeptSimpname(haDept.getSimpName());
                messageDeptEntity.setOrgId(haDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(haDept.getCreateDate());
                messageDeptEntity.setOrd(haDept.getOrd());
                messageDeptEntity.setState(haDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换淮安部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
