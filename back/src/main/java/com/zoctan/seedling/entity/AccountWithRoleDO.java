package com.zoctan.seedling.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 包含角色信息的账户实体
 *
 * @author Zoctan
 * @date 2018/07/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountWithRoleDO extends AccountDO {
  /** 账户的角色列表 */
  private List<RoleDO> roles;
}
