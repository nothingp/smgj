package com.szkingdom.entity;

import java.util.Date;

/**
 * @author Lange
 * @date 2018-12-21 11:08
 */
public class UserRole {
    private Long userRoleId;
    private Long roleId;
    private Long userId;
    private Date createTime;
    private Date updateTime;

    public UserRole(){

    }

    public UserRole(Long userRoleId, Long roleId, Long userId, Date createTime, Date updateTime) {
        this.userRoleId = userRoleId;
        this.roleId = roleId;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
