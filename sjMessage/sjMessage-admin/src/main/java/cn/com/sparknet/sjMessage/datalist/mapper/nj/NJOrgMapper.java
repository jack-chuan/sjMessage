package cn.com.sparknet.sjMessage.datalist.mapper.nj;

import cn.com.sparknet.sjMessage.datalist.entity.nj.NJOrgEntity;
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
public interface NJOrgMapper extends BaseMapper<NJOrgEntity> {

    List<NJOrgEntity> queryAllList();
	
}
