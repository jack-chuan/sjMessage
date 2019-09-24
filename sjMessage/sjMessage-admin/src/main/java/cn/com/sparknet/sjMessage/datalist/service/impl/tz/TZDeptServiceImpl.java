package cn.com.sparknet.sjMessage.datalist.service.impl.tz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.tz.TZDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.tz.TZDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("tzDeptService")
public class TZDeptServiceImpl extends ServiceImpl<TZDeptMapper, TZDeptEntity> implements TZDeptService {

    @Autowired
    private TZDeptMapper tzDeptMapper;

    @Override
    public List<TZDeptEntity> queryAllList() {
        try {
            return tzDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询泰州部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<TZDeptEntity> tzDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (TZDeptEntity tzDept : tzDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(tzDept.getDeptId());
                messageDeptEntity.setParentDeptId(tzDept.getParentDeptId());
                messageDeptEntity.setDeptCode(tzDept.getDeptCode());
                messageDeptEntity.setDeptName(tzDept.getDeptName());
                messageDeptEntity.setDeptSimpname(tzDept.getSimpName());
                messageDeptEntity.setOrgId(tzDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(tzDept.getCreateDate());
                messageDeptEntity.setOrd(tzDept.getOrd());
                messageDeptEntity.setState(tzDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换泰州部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
