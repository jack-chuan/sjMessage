package cn.com.sparknet.sjMessage.datalist.service.yz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZPersonEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 人员表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:45:12
 */
public interface YZPersonService extends IService<YZPersonEntity> {

    List<YZPersonEntity> queryAllList();

    List<MsPersonEntity> changeList(List<YZPersonEntity> yzPersonEntityList);
}

