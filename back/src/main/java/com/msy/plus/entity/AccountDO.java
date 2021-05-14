package com.msy.plus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author MoShuying
 * @date 2018/05/27
 */
@Data
@Table(name = "account")
public class AccountDO {
  /** 账户Id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /** 邮箱 */
  @Column(name = "email")
  private String email;

  /** 账户名 */
  @Column(name = "name")
  private String name;

  /** 密码 */
  @Column(name = "password")
  private String password;

  /** 注册时间 */
  @Column(name = "register_time")
  private Timestamp registerTime;

  /** 上一次登录时间 */
  @Column(name = "login_time")
  private Timestamp loginTime;
}
