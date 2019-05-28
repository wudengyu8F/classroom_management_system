package com.yizhuoyang.classroomfeatures.component;

import com.yizhuoyang.classroomfeatures.cache.RedisCacheManager;
import com.yizhuoyang.classroomfeatures.filter.MyLogoutFilter;
import com.yizhuoyang.classroomfeatures.filter.RolesOrFilter;
import com.yizhuoyang.classroomfeatures.session.MySessionManager;
import com.yizhuoyang.classroomfeatures.session.RedisSessionDao;
import com.yizhuoyang.classroomfeatures.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> stringFilterHashMap = new HashMap<>(16);
        stringFilterHashMap.put("roles", rolesOrFilter());
        stringFilterHashMap.put("logout", myLogoutFilter());
        shiroFilterFactoryBean.setFilters(stringFilterHashMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //注意过滤器配置顺序 不能颠倒
        filterChainDefinitionMap.put("/api/login/logout", "logout");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        //认证过滤器 authc--认证  anon--不需要认证
        //角色过滤器  审批页面进行一个角色判断
        //TODO 打开该代码
        filterChainDefinitionMap.put("/rsv/high/*", "roles[admin]");
        filterChainDefinitionMap.put("/login/*", "anon");
        //TODO 打开该代码
        filterChainDefinitionMap.put("/**", "authc");
//        //登录地址
        shiroFilterFactoryBean.setLoginUrl("/api/login/subLogin");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/login/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法: MD5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        return hashedCredentialsMatcher;
    }

    @Bean("myShiroRealm")
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
    //创建securityManager对象
    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean("sessionManager")
    public MySessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDao());
        return mySessionManager;
    }

    @Bean("cacheManager")
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }
    //自动登陆
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    private MyLogoutFilter myLogoutFilter() {
        MyLogoutFilter myLogoutFilter = new MyLogoutFilter();
        myLogoutFilter.setRedirectUrl("/login/out");
        myLogoutFilter.setPostOnlyLogout(true);
        return myLogoutFilter;
    }

    @Bean("rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(60 * 60 * 24 * 10);
        return simpleCookie;
    }

    @Bean("rolesOrFilter")
    public RolesOrFilter rolesOrFilter() {
        return new RolesOrFilter();
    }

    @Bean("redisSessionDao")
    public RedisSessionDao redisSessionDao() {
        return new RedisSessionDao();
    }

    @Bean("authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
            (@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
