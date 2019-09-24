package cn.com.sparknet.sjMessage.datalist.service.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * MSG部门表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-13 10:21:05
 */
public interface MsMainDeptService extends IService<MsMainDeptEntity> {

    int insertList(List<MsMainDeptEntity> msMainDeptEntityList);

    int deleteAll();

    List<MsMainDeptEntity> queryAllList();

    boolean saveEntity(MsMainDeptEntity msMainDeptEntity);

    boolean updateByEntity(MsMainDeptEntity msMainDeptEntity);
}

