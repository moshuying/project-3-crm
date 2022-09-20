package com.msy.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @TableName employee
 */
@TableName(value ="employee")
@Data
public class Employee implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @TableField(value = "name")
    private String name;

    /**
     *
     */
    @TableField(value = "password")
    private String password;

    /**
     *
     */
    @TableField(value = "email")
    private String email;

    /**
     *
     */
    @TableField(value = "age")
    private Integer age;

    /**
     *
     */
    @TableField(value = "dept")
    private Integer dept;

    /**
     * 入职时间
     */
    @TableField(value = "hireDate")
    private LocalDateTime hiredate;

    /**
     * 状态 1正常 0离职
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 超级管理员身份 1超管 0普通
     */
    @TableField(value = "admin")
    private Integer admin;

    /**
     *
     */
    @TableField(value = "login_time")
    private LocalDateTime loginTime;

    /**
     *
     */
    @TableField(value = "register_time")
    private LocalDateTime registerTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}