package com.shiro.realm;

import com.entity.User;
import com.service.UserService;
import com.util.serializable.MySimpleByteSource;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 授权实现
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        LOGGER.info("username: {}", username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));

        return authorizationInfo;
    }

    /**
     * 认证实现
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        LOGGER.info("username: {}", username);

        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();
        }

        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException();
        }

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), user.getPassword(),
//                ByteSource.Util.bytes(user.getCredentialsSalt()),
                new MySimpleByteSource(user.getCredentialsSalt()), //salt=username+salt,采用明文访问时，不需要此句
                getName() // realm name
        );
        return authenticationInfo;
    }
}
