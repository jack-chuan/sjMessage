package cn.com.sparknet.sjMessage.datalist.service.lyg;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:46:23
 */
public interface LYGUserService extends IService<LYGUserEntity> {

    List<LYGUserEntity> queryAllList();

    List<MsUserEntity> changeList(List<LYGUserEntity> lygUserEntityList);
}

