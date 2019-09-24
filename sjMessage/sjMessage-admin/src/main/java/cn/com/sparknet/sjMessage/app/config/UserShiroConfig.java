package cn.com.sparknet.sjMessage.app.config;

import cn.com.sparknet.sjMessage.app.realm.MsMainUserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: fast
 * @description: Shiro配置类
 * @author: YangQr
 * @create: 2019-05-27 17:28
 **/
@Configuration
public class UserShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(UserShiroConfig.class);

    /**
     * @Description: msMainUserAuthRealm 注入自定义的realm
     * @Param: []
     * @return: cn.com.sparknet.sjMessage.app.realm.MsMainUserRealm
     * @Author: YangQr
     * @Date: 19.5.27
     */
    @Bean
    public MsMainUserRealm msMainUserAuthRealm(){
        MsMainUserRealm msMainUserRealm = new MsMainUserRealm();
        logger.info("==== msMainUserRealm reg success ====");
        return  msMainUserRealm;
    }

    /** 
    * @Description: securityManager 注入安全管理器
    * @Param: [] 
    * @return: org.apache.shiro.mgt.SecurityManager 
    * @Author: YangQr
    * @Date: 19.5.27
    */ 
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(msMainUserAuthRealm());
        logger.info("==== securityManager reg success ====");
        return securityManager;
    }

    /** 
    * @Description: shiroFilter 注入Shiro过滤器
    * @Param: [securityManager] 配置安全管理器
    * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean 
    * @Author: YangQr
    * @Date: 19.5.27
    */ 
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置自定义的securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置默认登录的url，身份认证失败会访问该url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/success");
        // 设置未授权界面，权限认证失败会访问该url
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        // LinkedHashMap是有序的，进行顺序拦截器配置
        Map<String,String> filterChainMap = new LinkedHashMap<>();

        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/imgs/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/swagger-*/**", "anon");
        filterChainMap.put("/swagger-ui.html/**", "anon");
        // 登录url 放行
        filterChainMap.put("/login", "anon");

        // “/user/admin” 开头的需要身份认证
        filterChainMap.put("/user/admin*", "authc");
        // “/user/student” 开头的需要角色认证，是“admin”才允许
        filterChainMap.put("/user/student*/**", "roles[admin]");
        // “/user/teacher” 开头的需要权限认证，是“user:create”才允许
        filterChainMap.put("/user/teacher*/**", "perms[\"user:create\"]");
//        filterChainMap.put("/user/test*/**", "perms[\"student:create\"]");

        // 配置logout过滤器
        filterChainMap.put("/logout", "logout");
        // 所有url必须通过认证才可以访问，这行代码必须放在所有权限设置的最后
//        filterChainMap.put("/**", "authc");


        // 设置shiroFilterFactoryBean的FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        logger.info("====shiroFilterFactoryBean注册完成====");

        return shiroFilterFactoryBean;
    }

}
