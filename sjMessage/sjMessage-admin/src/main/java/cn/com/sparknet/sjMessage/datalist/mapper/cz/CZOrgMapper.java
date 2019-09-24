package cn.com.sparknet.sjMessage.datalist.mapper.cz;

import cn.com.sparknet.sjMessage.datalist.entity.cz.CZOrgEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 机构
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:44:13
 */
@Mapper
public interface CZOrgMapper extends BaseMapper<CZOrgEntity> {

    List<CZOrgEntity> queryAllList();
	
}
