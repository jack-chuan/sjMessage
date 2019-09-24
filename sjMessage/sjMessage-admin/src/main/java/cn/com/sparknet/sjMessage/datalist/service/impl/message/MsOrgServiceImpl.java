package cn.com.sparknet.sjMessage.datalist.service.impl.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsOrgMapper;
import cn.com.sparknet.sjMessage.datalist.service.message.MsOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("msOrgService")
public class MsOrgServiceImpl extends ServiceImpl<MsOrgMapper, MsOrgEntity> implements MsOrgService {

    @Autowired
    private MsOrgMapper msOrgMapper;

    @Override
    public int insertList(List<MsOrgEntity> messageOrgEntityList){
        try {
            return msOrgMapper.insertList(messageOrgEntityList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入组织信息失败");
        }
    }

    @Override
    public int deleteAll() {
        try {
            return msOrgMapper.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除同步表组织信息失败");
        }
    }

    @Override
    public List<MsOrgEntity> queryAllList() {
        try {
            return msOrgMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询同步表组织信息失败");
        }
    }

    @Override
    public boolean saveEntity(MsOrgEntity msOrgEntity) {
        try {
            return msOrgMapper.saveEntity(msOrgEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入同步表单个组织信息失败");
        }
    }
}
