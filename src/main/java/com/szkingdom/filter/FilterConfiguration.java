package com.szkingdom.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @author Lange
 * @date 2018-12-14 21:23
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean encodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new EncodingFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("encodingFilter");
        registration.setOrder(1);//设置优先级
        return registration;
    }


    @Bean
    public FilterRegistrationBean shiroFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilterFactoryBean");
        filterRegistrationBean.setFilter(proxy);
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }




}
