package com.szkingdom.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Lange
 * @date 2018-12-07 14:33
 */
@Controller
@Api("测试用的")
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="这是一个测试接口", notes="test: 这是一些提示信息")
    public String test(){
        return "success1";
    }

}
