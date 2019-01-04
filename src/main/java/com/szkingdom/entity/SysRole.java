package com.szkingdom.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-13 14:58
 */
public class SysRole implements Serializable {
    private Long roleId;

    @NotNull(message = "部门id不能为空")
    private Long deptId;

    @NotNull(message = "角色名称不能为空")
    @Pattern(regexp = "^[\\x{4e00}-\\x{9fa5}a-zA-Z0-9_-]{2,32}$", message = "角色名称由汉字、字母、数字、下划线，中划线组成，2到32位")
    private String roleName;
    private String roleCode;
    private Date createTime;
    private Date updateTime;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private List<SysPermission> permissionList;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }
}
