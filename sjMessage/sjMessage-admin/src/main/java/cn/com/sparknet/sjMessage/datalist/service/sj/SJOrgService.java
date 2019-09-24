package cn.com.sparknet.sjMessage.datalist.service.sj;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJOrgEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 机构
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:44:13
 */
public interface SJOrgService extends IService<SJOrgEntity> {

    List<SJOrgEntity> queryAllList(List<String> cityORGID);

    List<MsOrgEntity> changeList(List<SJOrgEntity> sjOrgEntityList);
}

