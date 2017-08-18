package com.example.demo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.example.demo.domain.Role;
import lombok.Data;

/**
 * 用户表
 */
@Data
public class User extends SuperEntity<User> {

    /**
     * 名称
     */
    @TableField("username")
    private String username;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 角色
     */
    @TableField("role")
    private Role role;

}
