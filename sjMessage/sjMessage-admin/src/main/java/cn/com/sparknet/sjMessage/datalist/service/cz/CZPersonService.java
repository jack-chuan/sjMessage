package cn.com.sparknet.sjMessage.datalist.service.cz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZPersonEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 人员表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:45:12
 */
public interface CZPersonService extends IService<CZPersonEntity> {

    List<CZPersonEntity> queryAllList();

    List<MsPersonEntity> changeList(List<CZPersonEntity> czPersonEntityList);
}

