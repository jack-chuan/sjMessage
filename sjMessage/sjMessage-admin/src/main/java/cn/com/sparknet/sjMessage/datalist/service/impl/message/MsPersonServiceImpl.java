package cn.com.sparknet.sjMessage.datalist.service.impl.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsPersonMapper;
import cn.com.sparknet.sjMessage.datalist.service.message.MsPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("msPersonService")
public class MsPersonServiceImpl extends ServiceImpl<MsPersonMapper, MsPersonEntity> implements MsPersonService {

    @Autowired
    private MsPersonMapper msPersonMapper;

    @Override
    public int insertList(List<MsPersonEntity> messagePersonEntityList) {
        try {
            return msPersonMapper.insertList(messagePersonEntityList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入人员信息失败");
        }
    }

    @Override
    public int deleteAll() {
        try {
            return msPersonMapper.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除同步表人员信息失败");
        }
    }

    @Override
    public List<MsPersonEntity> queryAllList() {
        try {
            return msPersonMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询同步表人员信息失败");
        }
    }

    @Override
    public boolean saveEntity(MsPersonEntity msPersonEntity) {
        try {
            return msPersonMapper.saveEntity(msPersonEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入同步表单个人员信息失败");
        }
    }
}
