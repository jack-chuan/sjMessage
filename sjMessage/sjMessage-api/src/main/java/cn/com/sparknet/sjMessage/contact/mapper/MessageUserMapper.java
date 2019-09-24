package cn.com.sparknet.sjMessage.contact.mapper;


import cn.com.sparknet.sjMessage.contact.entity.MessageUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * MSG用户表
 *
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@Mapper
public interface MessageUserMapper extends BaseMapper<MessageUserEntity> {

    List<HashMap> selectUserInfoByUserId(String userId);

    List<HashMap> selectUserInfoByDeptId(String deptId);

    List<HashMap> selectAllUserChildrenByDeptId();
}
