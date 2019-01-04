package com.szkingdom.mapper.dao;

import com.szkingdom.entity.DeptUser;
import com.szkingdom.entity.SysDept;
import com.szkingdom.entity.Users;
import com.szkingdom.entity.vo.IdIdListVo;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Long deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> listSysDept(SysDept dept);

    List<Users> listDeptUser(SysDept dept);

    void addDeptUserBatch(List<DeptUser> deptUserList);
}