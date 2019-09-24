package cn.com.sparknet.sjMessage.datalist.mapper.cz;

import cn.com.sparknet.sjMessage.datalist.entity.cz.CZDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:43:14
 */
@Mapper
public interface CZDeptMapper extends BaseMapper<CZDeptEntity> {

    List<CZDeptEntity> queryAllList();

}
