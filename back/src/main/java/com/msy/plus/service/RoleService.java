package com.msy.plus.service;

import com.msy.plus.core.service.Service;
import com.msy.plus.dto.RoleDTO;
import com.msy.plus.entity.RoleDO;
import com.msy.plus.entity.RoleWithPermissionDO;

/**
 * @author MoShuying
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
  RoleWithPermissionDO getDetailById(Long id);
}
