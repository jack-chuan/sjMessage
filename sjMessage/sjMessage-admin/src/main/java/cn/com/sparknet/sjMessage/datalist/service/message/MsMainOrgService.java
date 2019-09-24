package cn.com.sparknet.sjMessage.datalist.service.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainOrgEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * MSG机构表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:27:04
 */
public interface MsMainOrgService extends IService<MsMainOrgEntity> {

    int insertList(List<MsMainOrgEntity> msMainOrgEntityList);

    int deleteAll();

    List<MsMainOrgEntity> queryAllList();

    boolean saveEntity(MsMainOrgEntity msMainOrgEntity);

    boolean updateByEntity(MsMainOrgEntity msMainOrgEntity);

}

