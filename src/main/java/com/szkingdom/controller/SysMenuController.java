package com.szkingdom.controller;

import com.szkingdom.Result.Result;
import com.szkingdom.entity.SysMenu;
import com.szkingdom.service.interfaces.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Lange
 * @date 2018-12-21 14:11
 */
@Controller
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 角色列表
     * @return
     */
    @GetMapping("/listMenu")
    @ResponseBody
    public Result listMenu(){
        List<SysMenu> menuList = sysMenuService.listMenu();
        return Result.buildBaseSuccess(menuList);
    }


}
