package cn.com.sparknet.sjMessage.datalist.service.impl.yz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.yz.YZDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.yz.YZDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("yzDeptService")
public class YZDeptServiceImpl extends ServiceImpl<YZDeptMapper, YZDeptEntity> implements YZDeptService {

    @Autowired
    private YZDeptMapper yzDeptMapper;

    @Override
    public List<YZDeptEntity> queryAllList() {
        try {
            return yzDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询扬州部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<YZDeptEntity> yzDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (YZDeptEntity yzDept : yzDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(yzDept.getDeptId());
                messageDeptEntity.setParentDeptId(yzDept.getParentDeptId());
                messageDeptEntity.setDeptCode(yzDept.getDeptCode());
                messageDeptEntity.setDeptName(yzDept.getDeptName());
                messageDeptEntity.setDeptSimpname(yzDept.getSimpName());
                messageDeptEntity.setOrgId(yzDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(yzDept.getCreateDate());
                messageDeptEntity.setOrd(yzDept.getOrd());
                messageDeptEntity.setState(yzDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换扬州部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
