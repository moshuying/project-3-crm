package com.zoctan.seedling.service;

import com.zoctan.seedling.core.service.Service;
import com.zoctan.seedling.dto.RoleDTO;
import com.zoctan.seedling.entity.RoleDO;

/**
 * @author Zoctan
 * @date 2018/05/27
 */
public interface RoleService extends Service<RoleDO> {
  /**
   * 赋予默认角色给账户
   *
   * @param accountId 账户Id
   */
  void saveAsDefaultRole(Long accountId);

  /**
   * 保存角色
   *
   * @param roleDTO 角色传输实体
   */
  void save(RoleDTO roleDTO);

  /**
   * 更新角色
   *
   * @param roleDTO 角色传输实体
   */
  void update(RoleDTO roleDTO);
}
