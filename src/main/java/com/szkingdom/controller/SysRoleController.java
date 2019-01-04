package com.szkingdom.controller;

import com.szkingdom.Result.Result;
import com.szkingdom.annotation.CreUpdTime;
import com.szkingdom.entity.SysDept;
import com.szkingdom.entity.SysRole;
import com.szkingdom.entity.Users;
import com.szkingdom.entity.vo.IdIdListVo;
import com.szkingdom.service.interfaces.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-19 15:57
 */
@Controller
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 添加角色
     * @return
     */
    @PostMapping("/addRole")
    @ResponseBody
    public Result addRole(@Valid @CreUpdTime SysRole sysRole){
        sysRoleService.addRole(sysRole);
        return Result.buildBaseSuccess();
    }

    /**
     * 更新角色
     * @return
     */
    @PostMapping("/updateRole")
    @ResponseBody
    public Result updateRole(@Valid @CreUpdTime("update") SysRole sysRole){
        sysRoleService.updateRole(sysRole);
        return Result.buildBaseSuccess();
    }

    /**
     * 列表角色带部门
     * @return
     */
    @GetMapping("/listRoleAndDept")
    @ResponseBody
    public Result listRoleAndDept(){
        List<SysDept> deptList = sysRoleService.listRoleAndDept();
        return Result.buildBaseSuccess(deptList);
    }

    /**
     * 查看拥有某角色的所有用户
     * @param roleId
     * @return
     */
    @GetMapping("/listUserByRoleId")
    @ResponseBody
    public Result listUserByRoleId(@RequestParam("roleId") Long roleId){
        List<Users> deptList = sysRoleService.listUserByRoleId(roleId);
        return Result.buildBaseSuccess(deptList);
    }


    /**
     * 给用户赋予角色
     * @param idIdListVo
     * @return
     */
    @PostMapping("/grantRoleToUser")
    @ResponseBody
    public Result grantRoleToUser(@Valid IdIdListVo idIdListVo){
        sysRoleService.grantRoleToUser(idIdListVo);
        return Result.buildBaseSuccess();
    }

}
