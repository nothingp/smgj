package com.szkingdom.mapper.dao;

import com.szkingdom.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lange
 * @date 2018-11-05 11:15
 */
public interface PermissionMapper {


    List<SysPermission> selectPermissionByUserId(@Param("userId") Long userId);

    List<SysPermission> selectPermissionByUserName(@Param("username") String username);
}
