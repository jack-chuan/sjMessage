package cn.com.sparknet.sjMessage.datalist.service.nj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:46:23
 */
public interface NJUserService extends IService<NJUserEntity> {

    List<NJUserEntity> queryAllList();

    List<MsUserEntity> changeList(List<NJUserEntity> njUserEntityList);
}

