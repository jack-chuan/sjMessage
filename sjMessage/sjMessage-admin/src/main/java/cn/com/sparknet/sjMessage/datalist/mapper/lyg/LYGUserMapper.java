package cn.com.sparknet.sjMessage.datalist.mapper.lyg;

import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGUserEntity;
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
public interface LYGUserMapper extends BaseMapper<LYGUserEntity> {

    List<LYGUserEntity> queryAllList();
}
