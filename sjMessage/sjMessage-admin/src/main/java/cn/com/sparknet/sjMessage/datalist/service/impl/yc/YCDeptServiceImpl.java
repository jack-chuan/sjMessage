package cn.com.sparknet.sjMessage.datalist.service.impl.yc;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yc.YCDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.yc.YCDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.yc.YCDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("ycDeptService")
public class YCDeptServiceImpl extends ServiceImpl<YCDeptMapper, YCDeptEntity> implements YCDeptService {

    @Autowired
    private YCDeptMapper ycDeptMapper;

    @Override
    public List<YCDeptEntity> queryAllList() {
        try {
            return ycDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询盐城部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> changeList(List<YCDeptEntity> ycDeptEntityList) {
        List<MsDeptEntity> messageDeptEntityList = new ArrayList<MsDeptEntity>();
        MsDeptEntity messageDeptEntity = null;
        try {
            for (YCDeptEntity ycDept : ycDeptEntityList){
                messageDeptEntity = new MsDeptEntity();
                messageDeptEntity.setDeptId(ycDept.getDeptId());
                messageDeptEntity.setParentDeptId(ycDept.getParentDeptId());
                messageDeptEntity.setDeptCode(ycDept.getDeptCode());
                messageDeptEntity.setDeptName(ycDept.getDeptName());
                messageDeptEntity.setDeptSimpname(ycDept.getSimpName());
                messageDeptEntity.setOrgId(ycDept.getBelongOrgId());
                messageDeptEntity.setCreateDate(ycDept.getCreateDate());
                messageDeptEntity.setOrd(ycDept.getOrd());
                messageDeptEntity.setState(ycDept.getState());
                messageDeptEntityList.add(messageDeptEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换盐城部门为消息部门失败");
        }
        return messageDeptEntityList;
    }

}
