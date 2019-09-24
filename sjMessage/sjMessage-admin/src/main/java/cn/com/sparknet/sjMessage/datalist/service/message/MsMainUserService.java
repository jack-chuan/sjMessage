package cn.com.sparknet.sjMessage.datalist.service.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * MSG用户表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:30:03
 */
public interface MsMainUserService extends IService<MsMainUserEntity> {

    int insertList(List<MsMainUserEntity> msMainUserEntityList);

    int deleteAll();

    List<MsMainUserEntity> queryAllList();

    boolean saveEntity(MsMainUserEntity msMainUserEntity);

    boolean updateByEntity(MsMainUserEntity msMainUserEntity);

}

