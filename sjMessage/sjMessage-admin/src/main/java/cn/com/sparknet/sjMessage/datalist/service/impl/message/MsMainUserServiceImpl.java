package cn.com.sparknet.sjMessage.datalist.service.impl.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsMainUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.message.MsMainUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("msMainUserService")
public class MsMainUserServiceImpl extends ServiceImpl<MsMainUserMapper, MsMainUserEntity> implements MsMainUserService {

    @Autowired
    private MsMainUserMapper msMainUserMapper;

    @Override
    public int insertList(List<MsMainUserEntity> msMainUserEntity) {
        try {
            return msMainUserMapper.insertList(msMainUserEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入用户信息失败");
        }
    }

    @Override
    public int deleteAll() {
        try {
            return msMainUserMapper.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除主表用户信息失败");
        }
    }

    @Override
    public List<MsMainUserEntity> queryAllList() {
        try {
            return msMainUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询主表用户信息失败");
        }
    }

    @Override
    public boolean saveEntity(MsMainUserEntity msMainUserEntity) {
        try {
            return msMainUserMapper.saveEntity(msMainUserEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入主表单个用户信息失败");
        }
    }

    @Override
    public boolean updateByEntity(MsMainUserEntity msMainUserEntity) {
        try {
            return msMainUserMapper.updateByEntity(msMainUserEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改主表单个用户信息失败");
        }
    }
}
