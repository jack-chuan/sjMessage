package cn.com.sparknet.sjMessage.datalist.service.zj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:46:23
 */
public interface ZJUserService extends IService<ZJUserEntity> {

    List<ZJUserEntity> queryAllList();

    List<MsUserEntity> changeList(List<ZJUserEntity> zjUserEntityList);
}

