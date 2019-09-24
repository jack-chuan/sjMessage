package cn.com.sparknet.sjMessage.datalist.service.sq;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQOrgEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 机构
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:44:13
 */
public interface SQOrgService extends IService<SQOrgEntity> {

    List<SQOrgEntity> queryAllList();

    List<MsOrgEntity> changeList(List<SQOrgEntity> sqOrgEntityList);
}

