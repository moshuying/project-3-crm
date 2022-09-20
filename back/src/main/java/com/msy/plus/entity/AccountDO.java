package com.msy.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 *
 * @TableName employee
 * 主要用于账户登录，公用了员工表
 */
@TableName(value ="employee")
@Data
public class AccountDO {
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
  /** 注册时间 */
  @TableField(value = "register_time")
  private Timestamp registerTime;

  /** 上一次登录时间 */
  @TableField(value = "login_time")
  private Timestamp loginTime;
}
