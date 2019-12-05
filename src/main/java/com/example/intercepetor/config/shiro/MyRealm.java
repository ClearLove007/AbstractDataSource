package com.example.intercepetor.config.shiro;

import com.example.intercepetor.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 10:14 2019/12/4
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    private static Map userMap = new HashMap();

    static {
        userMap.put("admin",0);
        userMap.put("ADMIN",0);
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        log.info("正在对用户授权:{}", userInfo.getUserName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<String>();
        permissions.add("access:user");permissions.add("access:auth");
        info.setStringPermissions(permissions);
        log.info("授权完成：{}", userInfo.getUserName());
        return info;
    }

    /**
     * 验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String)token.getPrincipal();
        log.info("正在对用户验证:{}", userName);
        if (!userMap.containsKey(userName)){
            throw new UnknownAccountException("该用户没有注册");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo,"f6d55a291274c47f6660e32a53f5271c", ByteSource.Util.bytes("SAlt"), getName());
        log.info("验证完成:{}", userName);
        return info;
    }


}
