package cn.com.sparknet.sjMessage.datalist.service.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainPersonEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * MSG人员表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:28:10
 */
public interface MsMainPersonService extends IService<MsMainPersonEntity> {

    int insertList(List<MsMainPersonEntity> msMainPersonEntityList);

    int deleteAll();

    List<MsMainPersonEntity> queryAllList();

    boolean saveEntity(MsMainPersonEntity msMainPersonEntity);

    boolean updateByEntity(MsMainPersonEntity msMainPersonEntity);

}

