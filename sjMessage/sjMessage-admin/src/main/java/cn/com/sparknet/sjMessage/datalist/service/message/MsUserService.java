package cn.com.sparknet.sjMessage.datalist.service.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * MSG用户表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:30:03
 */
public interface MsUserService extends IService<MsUserEntity> {

    int insertList(List<MsUserEntity> messageUserEntityList);

    int deleteAll();

    List<MsUserEntity> queryAllList();

    boolean saveEntity(MsUserEntity msUserEntity);
}

