package cn.com.sparknet.sjMessage.datalist.service.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * MSG机构表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:27:04
 */
public interface MsOrgService extends IService<MsOrgEntity> {

    int insertList(List<MsOrgEntity> messageOrgEntityList);

    int deleteAll();

    List<MsOrgEntity> queryAllList();

    boolean saveEntity(MsOrgEntity msOrgEntity);
}

