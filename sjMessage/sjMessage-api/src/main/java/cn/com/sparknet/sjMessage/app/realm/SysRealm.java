package cn.com.sparknet.sjMessage.app.realm;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import cn.com.sparknet.sjMessage.app.entity.system.SysUser;
import cn.com.sparknet.sjMessage.app.mapper.system.SysUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @program: fast
 * @description: Realm system
 * @author: Leo Lee
 * @create: 2018-09-28 00:52
 **/
public class SysRealm extends AuthorizingRealm {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{

        String username = (String) authenticationToken.getPrincipal();

        //SysUser sysUser = sysUserMapper.getByUsername(username);
        RUser rUser = sysUserMapper.getByUsername(username);
        if(rUser != null){
            SecurityUtils.getSubject().getSession().setAttribute("rUser", rUser);

            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(rUser.getUserName(), rUser.getPassword(), "sysRealm");
            return authenticationInfo;
        } else {
            return null;
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
    	System.out.println("开始执行授权");
        String username = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        authorizationInfo.setRoles(sysUserMapper.getRoles(username));

        authorizationInfo.setStringPermissions(sysUserMapper.getMenus(username));

        return authorizationInfo;
    }
}
