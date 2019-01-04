package com.szkingdom.controller;

import com.szkingdom.Result.Result;
import com.szkingdom.entity.Users;
import com.szkingdom.redis.KeyPrefix;
import com.szkingdom.redis.RedisService;
import com.szkingdom.utils.CommonConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author Lange
 * @date 2018-12-13 17:31
 */
@Controller
public class LoginController {
    @Autowired
    private RedisService<String, Users> redisService;

    @PostMapping(value = "login")
    @ResponseBody
    public Result login(@Valid Users users){
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(users.getUsername(),users.getPassword());
        subject.login(token);
        Users loginUser = redisService.getValue(KeyPrefix.LOGIN_USER_KP.getPrefix() + users.getUsername());
        subject.getSession().setAttribute(CommonConst.USER_SESSION_KEY, loginUser);
        return Result.buildBaseSuccess();
    }

}
