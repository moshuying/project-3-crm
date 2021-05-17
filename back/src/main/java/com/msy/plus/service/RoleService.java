package com.msy.plus.service;

import com.msy.plus.core.service.Service;
import com.msy.plus.dto.RoleDTO;
import com.msy.plus.entity.Permission;
import com.msy.plus.entity.RoleDO;
import com.msy.plus.entity.RolePermissionDO;
import com.msy.plus.entity.RoleWithPermissionDO;

import java.util.List;

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

  /**
   * 获取角色信息并查询角色权限
   * @param id
   * @return
   */
  RoleWithPermissionDO getDetailById(Long id);

  /**
   * 保存用户权限
   * @param permissions
   */
  void savePermissions(Long roleId,List<Long> permissions);

  /**
   * 删除中间表信息
   * @param roleId
   * @param permissionId
   */
  void deleteRolePermissionItem(Long roleId,Long permissionId);

  /**
   * 获取所有权限表中的字段
   * @param roleId
   * @return List<RolePermissionDO>
   */
  List<RolePermissionDO> getAllRolePermissionTableRow(Long roleId);
}
