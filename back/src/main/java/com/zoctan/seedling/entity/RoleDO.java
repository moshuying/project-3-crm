package com.zoctan.seedling.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色实体
 *
 * @author Zoctan
 * @date 2018/05/27
 */
@Data
@Table(name = "role")
public class RoleDO {
  /** 角色Id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /** 角色名称 */
  @Column(name = "name")
  private String name;

  /** 角色编号 */
  @Column(name = "sn")
  private String sn;

  /** 角色权限 */
  @Column(name = "permission")
  private String permission;
}
