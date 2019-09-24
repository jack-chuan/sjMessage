package cn.com.sparknet.sjMessage.datalist.service.tz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:43:14
 */
public interface TZDeptService extends IService<TZDeptEntity> {

    List<TZDeptEntity> queryAllList();

    List<MsDeptEntity> changeList(List<TZDeptEntity> tzDeptEntityList);
}

