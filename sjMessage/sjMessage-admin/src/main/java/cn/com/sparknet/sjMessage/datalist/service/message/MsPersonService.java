package cn.com.sparknet.sjMessage.datalist.service.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * MSG人员表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:28:10
 */
public interface MsPersonService extends IService<MsPersonEntity> {

    int insertList(List<MsPersonEntity> messagePersonEntityList);

    int deleteAll();

    List<MsPersonEntity> queryAllList();

    boolean saveEntity(MsPersonEntity msPersonEntity);
}

