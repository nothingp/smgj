package com.szkingdom.service.interfaces;

import com.szkingdom.entity.SysPermission;

import java.util.List;

/**
 * @author Lange
 * @date 2018-12-14 11:08
 */
public interface PermissionService {
    List<SysPermission> selectPermissionByUserId(Long userId);
    List<SysPermission> selectPermissionByUserName(String userName);

}
