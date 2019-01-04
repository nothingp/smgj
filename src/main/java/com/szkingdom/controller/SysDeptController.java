package com.szkingdom.controller;

import com.szkingdom.Result.Result;
import com.szkingdom.annotation.CreUpdTime;
import com.szkingdom.entity.SysDept;
import com.szkingdom.entity.vo.IdIdListVo;
import com.szkingdom.service.interfaces.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-17 15:04
 */
@Controller
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @PostMapping("/addSysDept")
    @ResponseBody
    public Result addSysDept(@CreUpdTime @Valid SysDept dept){
        sysDeptService.addDept(dept);
        return Result.buildBaseSuccess();
    }

    /**
     * 给部门添加用户，支持批量
     * @param idIdListVo
     * @return
     */
    @PostMapping("/addDeptUser")
    @ResponseBody
    public Result addDeptUser(@Valid IdIdListVo idIdListVo){
        sysDeptService.addDeptUser(idIdListVo);
        return Result.buildBaseSuccess();
    }

    /**
     * 部门列表，带用户
     * @param dept
     * @return
     */
    @GetMapping("/listSysDeptAndUser")
    @ResponseBody
    public Result listSysDeptAndUser(SysDept dept){
        List<SysDept> deptList = sysDeptService.listSysDeptAndUser(dept);
        return Result.buildBaseSuccess(deptList);
    }

    /**
     * 部门列表，不带用户
     * @param dept
     * @return
     */
    @GetMapping("/listSysDeptNoUser")
    @ResponseBody
    public Result listSysDeptNoUser(SysDept dept){
        List<SysDept> deptList = sysDeptService.listSysDeptNoUser(dept);
        return Result.buildBaseSuccess(deptList);
    }

    /**
     * 更新部门
     * @param dept
     * @return
     */
    @PostMapping("/updateSysDept")
    @ResponseBody
    public Result updateSysDept(@CreUpdTime @Valid SysDept dept){
        sysDeptService.updateSysDept(dept);
        return Result.buildBaseSuccess();
    }

    /**
     * 删除部门
     * @param dept
     * @return
     */
    @PostMapping("/deleteSysDept")
    @ResponseBody
    public Result deleteSysDept(SysDept dept){
        sysDeptService.deleteSysDept(dept);
        return Result.buildBaseSuccess();
    }

}
