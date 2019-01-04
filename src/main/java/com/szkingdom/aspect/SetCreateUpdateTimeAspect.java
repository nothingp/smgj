package com.szkingdom.aspect;

import com.szkingdom.annotation.CreUpdTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 给注解CreUpdTime的参数设置创建时间和更新时间
 * @author Lange
 * @date 2018-12-20 19:50
 */
@Aspect
@Component
@Order(2)
public class SetCreateUpdateTimeAspect {

    @Pointcut(value = "execution(public * com.*.controller.*.*(..))")
    public void pointcut() {
    }


    @Before(value = "pointcut()")
    public void doBefore(JoinPoint joinPoint){
        Signature sig = joinPoint.getSignature();
        if (sig instanceof MethodSignature) {
            MethodSignature msig = (MethodSignature) sig;
            Object target = joinPoint.getTarget();
            try {
                Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
                currentMethod.getAnnotations();
                Annotation ass[][] = currentMethod.getParameterAnnotations();
                for(int i = 0; i < ass.length; i++){
                    for(Annotation a : ass[i]){
                        if(a.annotationType().equals(CreUpdTime.class)){
                            Object o = joinPoint.getArgs()[i];
                            Class clazz = o.getClass();
                            Date now = new Date();
                            CreUpdTime cu = (CreUpdTime)a;
                            String value = cu.value();
                            switch (value) {
                                case "all":
                                    clazz.getDeclaredMethod("setCreateTime", Date.class).invoke(o, now);
                                    clazz.getDeclaredMethod("setUpdateTime", Date.class).invoke(o, now);
                                    break;
                                case "create":
                                    clazz.getDeclaredMethod("setCreateTime", Date.class).invoke(o, now);
                                    break;
                                case "update":
                                    clazz.getDeclaredMethod("setUpdateTime", Date.class).invoke(o, now);
                                    break;
                            }
                        }
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
