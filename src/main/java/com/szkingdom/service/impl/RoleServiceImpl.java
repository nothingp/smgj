package com.szkingdom.service.impl;

import com.szkingdom.entity.SysRole;
import com.szkingdom.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lange
 * @date 2018-12-14 11:08
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public List<SysRole> selectRolesByUserId() {
        return null;
    }
}
