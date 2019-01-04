package com.szkingdom.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.szkingdom.Result.Message;
import com.szkingdom.Result.Result;
import com.szkingdom.entity.Users;
import com.szkingdom.entity.vo.PageQO;
import com.szkingdom.service.interfaces.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-07 15:23
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager redisCacheManager;

    @RequestMapping(value = "listUser",method = RequestMethod.GET)
    @ResponseBody
    public Result<Users> listUser(@Valid PageQO pageQO){
        Page<Users> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Users> users = userService.listUser();
        return Result.buildPageSuccess(page, users);
    }

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public Result addUser(@Valid Users users){
        try {
            userService.addUser(users);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return Result.buildBaseFail(Message.DUPLICATE_USERNAME);
        }
        return Result.buildBaseSuccess();
    }

    @RequestMapping(value = "toLogin",method = RequestMethod.GET)
    @ResponseBody
    public Result toLogin(){
        return Result.buildBaseFail(Message.RE_LOGIN);
    }

    @RequestMapping(value = "clearShiroCatch",method = RequestMethod.GET)
    @ResponseBody
    public Result clearShiroCatch(){
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        AuthorizingRealm realm = (AuthorizingRealm)rsm.getRealms().iterator().next();
        realm.getAuthorizationCache().clear();
        return Result.buildBaseSuccess();
    }


}
