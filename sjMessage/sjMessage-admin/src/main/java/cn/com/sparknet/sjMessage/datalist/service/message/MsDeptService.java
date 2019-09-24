package cn.com.sparknet.sjMessage.datalist.service.message;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * MSG部门表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-13 10:21:05
 */
public interface MsDeptService extends IService<MsDeptEntity> {

    int insertList(List<MsDeptEntity> messageDeptEntityList);

    int deleteAll();

    List<MsDeptEntity> queryAllList();

    boolean saveEntity(MsDeptEntity msDeptEntity);
}

