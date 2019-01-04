package com.szkingdom.service.interfaces;

import com.szkingdom.entity.SysDept;
import com.szkingdom.entity.SysRole;
import com.szkingdom.entity.Users;
import com.szkingdom.entity.vo.IdIdListVo;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-19 15:58
 */
public interface SysRoleService {

    List<SysDept> listRoleAndDept();

    List<Users> listUserByRoleId(Long roleId);

    void grantRoleToUser(IdIdListVo idIdListVo);

    void addRole(SysRole sysRole);

    void updateRole(SysRole sysRole);
}
