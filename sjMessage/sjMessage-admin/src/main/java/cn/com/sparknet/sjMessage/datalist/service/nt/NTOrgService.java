package cn.com.sparknet.sjMessage.datalist.service.nt;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTOrgEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 机构
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:44:13
 */
public interface NTOrgService extends IService<NTOrgEntity> {

    List<NTOrgEntity> queryAllList();

    List<MsOrgEntity> changeList(List<NTOrgEntity> ntOrgEntityList);
}

