package cn.com.sparknet.sjMessage.datalist.service.impl.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsUserMapper;
import cn.com.sparknet.sjMessage.datalist.service.message.MsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("msUserService")
public class MsUserServiceImpl extends ServiceImpl<MsUserMapper, MsUserEntity> implements MsUserService {

    @Autowired
    private MsUserMapper msUserMapper;

    @Override
    public int insertList(List<MsUserEntity> messageUserEntityList) {
        try {
            return msUserMapper.insertList(messageUserEntityList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入用户信息失败");
        }
    }

    @Override
    public int deleteAll() {
        try {
            return msUserMapper.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除同步表用户信息失败");
        }
    }

    @Override
    public List<MsUserEntity> queryAllList() {
        try {
            return msUserMapper.queryAllList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询同步表用户信息失败");
        }
    }

    @Override
    public boolean saveEntity(MsUserEntity msUserEntity) {
        try {
            return msUserMapper.saveEntity(msUserEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入同步表单个用户信息失败");
        }
    }
}
