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
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
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
//        Set<String> roles = getRolesByUserId(userId);
        //从数据库或者缓存获取权限信息
        Set<String> permissions = getPermissionsByUserId(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByUserId(String userId) {
        return userDao.getPermissionsByUserId(userId);
    }

    private Set<String> getRolesByUserId(String userId) {
        List<String> list = userDao.getRolesByUserId(userId);
        return new HashSet<>(list);
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
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userId,
                password, "myShiroRealm");
        //TODO 加盐
//        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userId));
        return authenticationInfo;
    }

    private String getPasswordByUserId(String userId) {
        return userDao.getPasswordByUserId(userId);
    }
}
