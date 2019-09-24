package cn.com.sparknet.sjMessage.datalist.service.impl.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsDeptMapper;
import cn.com.sparknet.sjMessage.datalist.service.message.MsDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("msDeptService")
public class MsDeptServiceImpl extends ServiceImpl<MsDeptMapper, MsDeptEntity> implements MsDeptService {

    @Autowired
    private MsDeptMapper msDeptMapper;

    @Override
    public int insertList(List<MsDeptEntity> messageDeptEntityList){
        try {
            return msDeptMapper.insertList(messageDeptEntityList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入部门信息失败");
        }
    }

    @Override
    public int deleteAll() {
        try {
            return msDeptMapper.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除同步表部门信息失败");
        }
    }

    @Override
    public List<MsDeptEntity> queryAllList() {
        try {
            return msDeptMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询同步表部门信息失败");
        }
    }

    @Override
    public boolean saveEntity(MsDeptEntity msDeptEntity) {
        try {
            return msDeptMapper.saveEntity(msDeptEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入同步表单个部门信息失败");
        }
    }
}
