package com.szkingdom.service.interfaces;

import com.szkingdom.entity.SysRole;

import java.util.List;

/**
 * @author Lange
 * @date 2018-12-14 11:08
 */
public interface RoleService {
    List<SysRole> selectRolesByUserId();

}
