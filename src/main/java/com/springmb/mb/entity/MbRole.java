package com.springmb.mb.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.springmb.mb.entity.base.DataEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author john
 * @date 2020/1/11 - 20:12
 */

@TableName(value = "sys_role")
public class MbRole extends DataEntity<MbRole> implements GrantedAuthority {
    private String roleId;
    private String  rolecode;
    private String  roleName;
    private String  roleDesc;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}