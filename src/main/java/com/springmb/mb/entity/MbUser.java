package com.springmb.mb.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 * @date 2020/1/11 - 20:15
 */
@TableName(value = "sys_user")
public class MbUser {
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    @TableField(exist = false)
    private List<MbRole> roles = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MbRole> getRoles() {
        return roles;
    }

    public void setRoles(List<MbRole> roles) {
        this.roles = roles;
    }
}