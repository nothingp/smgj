package com.szkingdom.filter;

import com.alibaba.fastjson.JSONObject;
import com.szkingdom.Result.Message;
import com.szkingdom.Result.Result;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Lange
 * @date 2018-12-14 17:55
 */
public class ResourceCheckFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String url = getPathWithinApplication(servletRequest);
        Subject subject = getSubject(servletRequest,servletResponse);
        return subject.isPermitted(url);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // todo 日志
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        response.getWriter().print(JSONObject.toJSONString(Result.buildBaseFail(Message.NO_PERMISSION)));
        return false;
    }
}
