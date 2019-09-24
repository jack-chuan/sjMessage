package cn.com.sparknet.sjMessage.app.mapper.system;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import cn.com.sparknet.sjMessage.app.entity.system.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface SysUserMapper {

    int insert(SysUser record);

    int insertSelective(SysUser record);

    //@Select(" select * from sys_user where username = #{username} ")
    @Select(" select * from r$user where username = #{username} ")
    RUser getByUsername(String username);

    @Select(" select r.role_name from sys_role r, sys_user_role ur, sys_user u " +
            " where r.role_id = ur.role_id and ur.user_id = u.user_id and u.username = #{username} ")
    Set<String> getRoles(String username);

    @Select(" select m.menu_name from sys_menu m, sys_role_menu rm, sys_user_role ur, sys_user " +
            " where m.menu_id = rm.menu_id and  rm.role_id = ur.role.id and ur.user_id = u.user_id " +
            " and u.username = #{username} ")
    Set<String> getMenus(String username);
}