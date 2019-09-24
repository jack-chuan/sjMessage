package cn.com.sparknet.sjMessage.datalist.service.cz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:46:23
 */
public interface CZUserService extends IService<CZUserEntity> {

    List<CZUserEntity> queryAllList();

    List<MsUserEntity> changeList(List<CZUserEntity> czUserEntityList);
}

