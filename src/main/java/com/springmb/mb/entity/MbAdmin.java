package com.springmb.mb.entity;

import com.springmb.mb.entity.base.DataEntity;

public class MbAdmin extends DataEntity<MbAdmin> {
    private String adminId;
    private String adminName;
    private String roleId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
