package cn.com.sparknet.sjMessage.datalist.service.impl.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsMainDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.message.MsMainDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("msMainDeptService")
public class MsMainDeptServiceImpl extends ServiceImpl<MsMainDeptMapper, MsMainDeptEntity> implements MsMainDeptService {

    @Autowired
    private MsMainDeptMapper msMainDeptMapper;

    @Override
    public int insertList(List<MsMainDeptEntity> msMainDeptEntity){
        try {
            return msMainDeptMapper.insertList(msMainDeptEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入部门信息失败");
        }
    }

    @Override
    public int deleteAll() {
        try {
            return msMainDeptMapper.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除主表部门信息失败");
        }
    }

    @Override
    public List<MsMainDeptEntity> queryAllList() {
        try {
            return msMainDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询主表部门信息失败");
        }
    }

    @Override
    public boolean saveEntity(MsMainDeptEntity msMainDeptEntity) {
        try {
            return msMainDeptMapper.saveEntity(msMainDeptEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入主表单个部门信息失败");
        }
    }

    @Override
    public boolean updateByEntity(MsMainDeptEntity msMainDeptEntity) {
        try {
            return msMainDeptMapper.updateByEntity(msMainDeptEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改主表单个部门信息失败");
        }
    }
}
