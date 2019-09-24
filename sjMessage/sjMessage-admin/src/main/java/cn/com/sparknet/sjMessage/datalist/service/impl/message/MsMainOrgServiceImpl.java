package cn.com.sparknet.sjMessage.datalist.service.impl.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsMainOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.message.MsMainOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("msMainOrgService")
public class MsMainOrgServiceImpl extends ServiceImpl<MsMainOrgMapper, MsMainOrgEntity> implements MsMainOrgService {

    @Autowired
    private MsMainOrgMapper msMainOrgMapper;

    @Override
    public int insertList(List<MsMainOrgEntity> msMainOrgEntity){
        try {
            return msMainOrgMapper.insertList(msMainOrgEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入组织信息失败");
        }
    }

    @Override
    public int deleteAll() {
        try {
            return msMainOrgMapper.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除主表组织信息失败");
        }
    }

    @Override
    public List<MsMainOrgEntity> queryAllList() {
        try {
            return msMainOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询主表组织信息失败");
        }
    }

    @Override
    public boolean saveEntity(MsMainOrgEntity msMainOrgEntity) {
        try {
            return msMainOrgMapper.saveEntity(msMainOrgEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入主表单个组织信息失败");
        }
    }

    @Override
    public boolean updateByEntity(MsMainOrgEntity msMainOrgEntity) {
        try {
            return msMainOrgMapper.updateByEntity(msMainOrgEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改主表单个组织信息失败");
        }
    }
}
