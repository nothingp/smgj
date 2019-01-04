package com.szkingdom.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

public class SysDept {
    private Long deptId;

    private Long parentId;

    @NotNull(message = "部门名称不能为空")
    @Pattern(regexp = "^[\\x{4e00}-\\x{9fa5}a-zA-Z0-9_-]{4,32}$", message = "部门名称由汉字、字母、数字、下划线，中划线组成，4到32位")
    private String deptName;

    @NotNull(message = "部门编号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,32}$", message = "部门编号由字母、数字、下划线，中划线组成，4到32位")
    private String deptCode;

    private String chargePerson;

    private String linkman;

    private String phoneNumber;

    private String cellphoneNumber;

    private String email;

    private Date createTime;

    private Date updateTime;


    /**
     * 子部门
     */
    private List<SysDept> childDeptList;
    /**
     * 部门员工
     */
    private List<Users> userList;

    /**
     * 角色列表
     */
    private List<SysRole> roleList;

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysDept> getChildDeptList() {
        return childDeptList;
    }

    public void setChildDeptList(List<SysDept> childDeptList) {
        this.childDeptList = childDeptList;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}