package cn.com.sparknet.sjMessage.datalist.service.impl.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsMainPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.message.MsMainPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("msMainPersonService")
public class MsMainPersonServiceImpl extends ServiceImpl<MsMainPersonMapper, MsMainPersonEntity> implements MsMainPersonService {

    @Autowired
    private MsMainPersonMapper msMainPersonMapper;

    @Override
    public int insertList(List<MsMainPersonEntity> msMainPersonEntity) {
        try {
            return msMainPersonMapper.insertList(msMainPersonEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入人员信息失败");
        }
    }

    @Override
    public int deleteAll() {
        try {
            return msMainPersonMapper.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除主表人员信息失败");
        }
    }

    @Override
    public List<MsMainPersonEntity> queryAllList() {
        try {
            return msMainPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询主表人员信息失败");
        }
    }

    @Override
    public boolean saveEntity(MsMainPersonEntity msMainPersonEntity) {
        try {
            return msMainPersonMapper.saveEntity(msMainPersonEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入主表单个人员信息失败");
        }
    }

    @Override
    public boolean updateByEntity(MsMainPersonEntity msMainPersonEntity) {
        try {
            return msMainPersonMapper.updateByEntity(msMainPersonEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改主表单个人员信息失败");
        }
    }
}
