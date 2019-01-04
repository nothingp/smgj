package com.szkingdom.mapper.dao;

import com.szkingdom.entity.UserRole;
import com.szkingdom.entity.SysRole;

import java.util.List;

/**
 * @author Lange
 * @date 2018-11-05 11:15
 */
public interface RoleMapper {

    List<SysRole> listRole();


    void grantRoleToUser(List<UserRole> userRoleList);

    void addRole(SysRole sysRole);

    void updateRole(SysRole sysRole);
}
