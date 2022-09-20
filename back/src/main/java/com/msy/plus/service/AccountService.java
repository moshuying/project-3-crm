package com.msy.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msy.plus.dto.AccountDTO;
import com.msy.plus.entity.AccountDO;
import com.msy.plus.entity.AccountWithRoleDO;

/**
 * @author MoShuying
 * @date 2018/05/27
 */
public interface AccountService extends IService<AccountDO> {
  /**
   * 保存账户
   *
   * @param accountDTO 账户传输实体
   */
  void save(AccountDTO accountDTO);

  /**
   * 按账户名查询带有角色信息的账户
   *
   * @param name 账户名
   * @return 账户
   */
  AccountWithRoleDO getByNameWithRole(String name);

  /**
   * 按账户Id查询带有角色信息的账户
   *
   * @param id 账户Id
   * @return 账户
   */
  AccountWithRoleDO getByIdWithRole(Long id);

  /**
   * 更新账户
   *
   * @param accountDTO 账户传输实体
   */
  void updateByName(AccountDTO accountDTO);

  /**
   * 按账户名更新最后一次登录时间
   *
   * @param name 账户名
   * @return 是否更新成功
   */
  boolean updateLoginTimeByName(String name);

  /**
   * 验证账户密码
   *
   * @param rawPassword 原密码
   * @param encodedPassword 加密后的密码
   * @return {boolean}
   */
  boolean verifyPassword(String rawPassword, String encodedPassword);
}
