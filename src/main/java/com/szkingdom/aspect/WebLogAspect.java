package com.szkingdom.aspect;

import com.alibaba.fastjson.JSONObject;
import com.szkingdom.annotation.CreUpdTime;
import com.szkingdom.entity.SysLog;
import com.szkingdom.entity.Users;
import com.szkingdom.service.interfaces.SysLogService;
import com.szkingdom.utils.CommonConst;
import com.szkingdom.utils.CommonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author Lange
 * @date 2018-12-15 11:28
 */

@Aspect
@Component
@Order(1)
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    private ThreadLocal<Long> startTime = new ThreadLocal<>();  //线程副本类去记录各个线程的开始时间

    @Autowired
    private SysLogService sysLogService;

    @Around(value = "execution(public * com.*.controller.*.*(..))")
    public Object exec(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String reqUrl = request.getRequestURL().toString();
        String reqMethod = request.getMethod();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String method = className + "." + joinPoint.getSignature().getName();
        String methodParamJson = JSONObject.toJSONString(joinPoint.getArgs());
        String reqParam = JSONObject.toJSONString(request.getParameterMap());
        logger.info("请求URL:" + reqUrl);// 想那个url发的请求
        logger.info("请求METHOD:" + reqMethod);
        logger.info("请求方法:" + method);// 请求的是哪个类，哪种方法
        logger.info("请求方法参数:" + methodParamJson);// 方法本传了哪些参数
        logger.info("请求request参数:" + reqParam);// 方法本传了哪些参数
        Object result = joinPoint.proceed();
        String resultJson = JSONObject.toJSONString(result);
        Long spendTime = System.currentTimeMillis() - startTime;
        logger.info("返回结果:" + resultJson);// 响应的内容---方法的返回值responseEntity
        logger.info("用时:" + spendTime + "ms");
        SysLog log = new SysLog();
        log.setLogId(CommonUtils.generatePK());
        log.setMethodName(method);
        Users user = (Users)request.getSession().getAttribute(CommonConst.USER_SESSION_KEY);
        if(user != null){
            log.setOperateUserId(user.getUserId());
        }
        log.setRequestUrl(reqUrl);
        Date now = new Date();
        log.setUpdateTime(now);
        log.setCreateTime(now);
        log.setClassName(className);
        log.setIp(CommonUtils.getIpAddr(request));
        log.setRequestParam(reqParam);
        log.setMethodParam(methodParamJson);
        log.setResponseJson(resultJson);
        sysLogService.addLog(log);
        return result;
    }
}
