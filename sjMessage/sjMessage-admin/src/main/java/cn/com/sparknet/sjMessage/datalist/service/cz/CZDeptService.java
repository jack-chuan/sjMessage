package cn.com.sparknet.sjMessage.datalist.service.cz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:43:14
 */
public interface CZDeptService extends IService<CZDeptEntity> {

    List<CZDeptEntity> queryAllList();

    List<MsDeptEntity> changeList(List<CZDeptEntity> czDeptEntityList);
}

