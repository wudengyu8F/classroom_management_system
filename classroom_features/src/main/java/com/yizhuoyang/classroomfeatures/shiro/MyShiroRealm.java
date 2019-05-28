package com.yizhuoyang.classroomfeatures.shiro;

import com.yizhuoyang.classroomfeatures.dao.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = (String) principalCollection.getPrimaryPrincipal();
        //从数据库或者缓存获取角色信息
        Set<String> roles = getRolesByUserId(userId);
        //从数据库或者缓存获取权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getRolesByUserId(String userId) {
        return userDao.getRolesByUserId(userId);
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1. 从主体传过来的认证信息中,获得用户名
        String userId = (String) authenticationToken.getPrincipal();
        //2.通过用户名到数据库获取凭证
        String password = getPasswordByUserId(userId);
        if ("".equals(password)) {
            return null;
        }
        //返回信息
        return new SimpleAuthenticationInfo(userId, password, "myShiroRealm");
    }
    //模拟数据库查询凭证
    private String getPasswordByUserId(String userId) {
        return userDao.getPasswordByUserId(userId);
    }
}
