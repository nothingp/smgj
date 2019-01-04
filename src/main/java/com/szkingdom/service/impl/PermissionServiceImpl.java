package com.szkingdom.service.impl;

import com.szkingdom.entity.SysPermission;
import com.szkingdom.mapper.dao.PermissionMapper;
import com.szkingdom.service.interfaces.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lange
 * @date 2018-12-14 11:08
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<SysPermission> selectPermissionByUserId(Long userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }

    @Override
    public List<SysPermission> selectPermissionByUserName(String username) {
        return permissionMapper.selectPermissionByUserName(username);
    }
}
