package com.szkingdom.entity;

/**
 * @author Lange
 * @date 2018-12-13 14:59
 */
public class SysPermission {
    private Long permissionId;
    private String permissionName;
    private String url;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
