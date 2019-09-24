package cn.com.sparknet.sjMessage.datalist.service.sz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:43:14
 */
public interface SZDeptService extends IService<SZDeptEntity> {

    List<SZDeptEntity> queryAllList();

    List<MsDeptEntity> changeList(List<SZDeptEntity> szDeptEntityList);
}

