package cn.com.sparknet.sjMessage.datalist.service.sz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZPersonEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 人员表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:45:12
 */
public interface SZPersonService extends IService<SZPersonEntity> {

    List<SZPersonEntity> queryAllList();

    List<MsPersonEntity> changeList(List<SZPersonEntity> szPersonEntityList);
}

