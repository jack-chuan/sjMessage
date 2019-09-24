package cn.com.sparknet.sjMessage.datalist.mapper.sj;

import cn.com.sparknet.sjMessage.datalist.entity.sj.SJUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:46:23
 */
@Mapper
public interface SJUserMapper extends BaseMapper<SJUserEntity> {

    List<SJUserEntity> queryAllList();
}
