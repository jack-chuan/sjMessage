package cn.com.sparknet.sjMessage.datalist.service.impl.sq;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.sq.SQDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.sq.SQDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sqDeptService")
public class SQDeptServiceImpl extends ServiceImpl<SQDeptMapper, SQDeptEntity> implements SQDeptService {

    @Autowired
    private SQDeptMapper sqDeptMapper;

    @Override
    public List<SQDeptEntity> queryAllList() {
        try {
            return sqDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询宿迁部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<SQDeptEntity> sqDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (SQDeptEntity sqDept : sqDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(sqDept.getDeptId());
                messageDeptEntity.setParentDeptId(sqDept.getParentDeptId());
                messageDeptEntity.setDeptCode(sqDept.getDeptCode());
                messageDeptEntity.setDeptName(sqDept.getDeptName());
                messageDeptEntity.setDeptSimpname(sqDept.getSimpName());
                messageDeptEntity.setOrgId(sqDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(sqDept.getCreateDate());
                messageDeptEntity.setOrd(sqDept.getOrd());
                messageDeptEntity.setState(sqDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换宿迁部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
