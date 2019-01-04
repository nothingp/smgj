package com.szkingdom.entity;

import java.util.Date;

/**
 * @author Lange
 * @date 2018-12-20 10:08
 */
public class DeptUser {
    private Long deptUserId;
    private Long deptId;
    private Long userId;
    private Date createTime;
    private Date updateTime;

    public DeptUser(){

    }
    public DeptUser(Long deptUserId, Long deptId, Long userId, Date createTime, Date updateTime) {
        this.deptUserId = deptUserId;
        this.deptId = deptId;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public Long getDeptUserId() {
        return deptUserId;
    }

    public void setDeptUserId(Long deptUserId) {
        this.deptUserId = deptUserId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
