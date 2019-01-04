package com.szkingdom.exception;

import com.szkingdom.Result.Result;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @author Lange
 * @date 2018-12-10 18:15
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindExceptionHandler(HttpServletRequest request, BindException exception) {
        return Result.buildBaseFail(exception.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        return Result.buildBaseFail(exception.getMessage());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }



}
