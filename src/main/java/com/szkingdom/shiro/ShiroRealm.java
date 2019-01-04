package com.szkingdom.shiro;

import com.szkingdom.entity.SysPermission;
import com.szkingdom.entity.Users;
import com.szkingdom.redis.KeyPrefix;
import com.szkingdom.redis.RedisService;
import com.szkingdom.service.interfaces.PermissionService;
import com.szkingdom.service.interfaces.UserService;
import com.szkingdom.utils.CommonConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-12 13:49
 */
public class ShiroRealm extends AuthorizingRealm{

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RedisService<String,Users> redisService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Users loginUser = (Users)SecurityUtils.getSubject().getSession().getAttribute(CommonConst.USER_SESSION_KEY);
        List<SysPermission> permissionList = permissionService.selectPermissionByUserId(loginUser.getUserId());
        for (SysPermission permission : permissionList) {
            authorizationInfo.addStringPermission(permission.getUrl());
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        Users userInfo = userService.findByUsername(username);
        if (userInfo == null) {
            return null;
        }
        redisService.setValue(KeyPrefix.LOGIN_USER_KP, userInfo.getUsername(), userInfo);
        return new SimpleAuthenticationInfo(
            username, //用户名
            userInfo.getPassword(), //密码
            ByteSource.Util.bytes(userInfo.getUsername() + userInfo.getSalt()), //盐
            getName()  //realm name
        );
    }

}
