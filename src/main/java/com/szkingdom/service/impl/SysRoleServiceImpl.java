package com.szkingdom.service.impl;

import com.szkingdom.entity.*;
import com.szkingdom.entity.vo.IdIdListVo;
import com.szkingdom.mapper.dao.RoleMapper;
import com.szkingdom.mapper.dao.SysDeptMapper;
import com.szkingdom.mapper.dao.UserMapper;
import com.szkingdom.service.interfaces.SysRoleService;
import com.szkingdom.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-19 15:59
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addRole(SysRole sysRole) {
        sysRole.setRoleId(CommonUtils.generatePK());
        roleMapper.addRole(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole) {
        roleMapper.updateRole(sysRole);
    }

    @Override
    public void grantRoleToUser(IdIdListVo idIdListVo) {
        List<UserRole> userRoleList = new ArrayList<>();
        for(String id : idIdListVo.getIdArray()){
            Date now = new Date();
            UserRole ur = new UserRole(CommonUtils.generatePK(), Long.parseLong(id), idIdListVo.getId(), now, now);
            userRoleList.add(ur);
        }
        roleMapper.grantRoleToUser(userRoleList);
    }

    @Override
    public List<Users> listUserByRoleId(Long roleId) {
        return userMapper.listUserByRoleId(roleId);
    }


    @Override
    public List<SysDept> listRoleAndDept() {
        List<SysDept> deptList = sysDeptMapper.listSysDept(null);
        List<SysRole> roleList = roleMapper.listRole();
        return deptAndRoleTree(deptList,roleList);
    }


    private List<SysDept> deptAndRoleTree(List<SysDept> allDept, List<SysRole> allRole){
        List<SysDept> returnList = new ArrayList<>();
        for(SysDept d : allDept){
            if(d.getParentId() == null){
                returnList.add(d);
                setChild(d, allDept, allRole);
            }
        }
        return returnList;
    }

    private void setChild(SysDept parent, List<SysDept> allDept, List<SysRole> allRole){
        setDeptRole(parent, allRole);
        List<SysDept> child = new ArrayList<>();
        for(SysDept c : allDept){
            if(parent.getDeptId().equals(c.getParentId())){
                child.add(c);
                setChild(c, allDept, allRole);
            }
        }
        parent.setChildDeptList(child);
    }

    private void setDeptRole(SysDept parent, List<SysRole> allRole) {
        List<SysRole> childRoles = new ArrayList<>();
        for(SysRole r : allRole){
            if(parent.getDeptId().equals(r.getDeptId())){
                childRoles.add(r);
            }
        }
        allRole.removeAll(childRoles);
        parent.setRoleList(childRoles);
    }
}
