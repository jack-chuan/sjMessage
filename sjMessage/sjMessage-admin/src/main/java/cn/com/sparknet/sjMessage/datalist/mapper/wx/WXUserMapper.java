package cn.com.sparknet.sjMessage.datalist.mapper.wx;

import cn.com.sparknet.sjMessage.datalist.entity.wx.WXUserEntity;
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
public interface WXUserMapper extends BaseMapper<WXUserEntity> {

    List<WXUserEntity> queryAllList();
}
