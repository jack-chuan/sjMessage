package cn.com.sparknet.sjMessage.app.mapper;

import cn.com.sparknet.sjMessage.app.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    List<User> selectAllUser();

    User selectByUserName(String userName);

    @Select("select phone from t_user where user_name = #{userName}")
    String selectPhoneByUserName(String userName);
}