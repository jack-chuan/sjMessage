package cn.com.sparknet.sjMessage.app.realm;

import cn.com.sparknet.sjMessage.datalist.entity.message.MsMainUserEntity;
import cn.com.sparknet.sjMessage.datalist.mapper.message.MsMainUserMapper;
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
 * @author: YangQr
 * @create: 2019-05-27 16:50
 **/
public class MsMainUserRealm extends AuthorizingRealm {

    @Resource
    private MsMainUserMapper msMainUserMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{

        String username = (String) authenticationToken.getPrincipal();

        MsMainUserEntity msMainUserEntity = msMainUserMapper.getMsMainUserEntity(username);

        if(msMainUserEntity != null){
            SecurityUtils.getSubject().getSession().setAttribute("sysUser", msMainUserEntity);

            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(msMainUserEntity.getUsername(), msMainUserEntity.getPassword(), "msMainUserRealm");

            return authenticationInfo;
        } else {
            return null;
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){

        String username = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

//        authorizationInfo.setRoles(sysUserMapper.getRoles(username));

//        authorizationInfo.setStringPermissions(sysUserMapper.getMenus(username));

        return authorizationInfo;
    }
}
