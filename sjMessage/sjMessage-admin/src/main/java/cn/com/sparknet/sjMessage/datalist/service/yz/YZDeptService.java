package cn.com.sparknet.sjMessage.datalist.service.yz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:43:14
 */
public interface YZDeptService extends IService<YZDeptEntity> {

    List<YZDeptEntity> queryAllList();

    List<MsDeptEntity> changeList(List<YZDeptEntity> yzDeptEntityList);
}

