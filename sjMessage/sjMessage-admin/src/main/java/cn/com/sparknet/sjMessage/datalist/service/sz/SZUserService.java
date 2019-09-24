package cn.com.sparknet.sjMessage.datalist.service.sz;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:46:23
 */
public interface SZUserService extends IService<SZUserEntity> {

    List<SZUserEntity> queryAllList();

    List<MsUserEntity> changeList(List<SZUserEntity> szUserEntityList);
}

