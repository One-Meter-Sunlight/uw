package com.example.demo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * 用户表
 */
public class User extends SuperEntity<User> {

    /**
     * 名称
     */
    @TableField("username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User [id=" + this.getId() + ", username=" + username + "]";
    }

}
