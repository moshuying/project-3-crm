package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.Permission;
import com.msy.plus.entity.RoleDO;
import com.msy.plus.entity.RolePermissionDO;
import com.msy.plus.entity.RoleWithPermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MoShuying
 * @date 2018/07/15
 */
public interface RoleMapper extends MyMapper<RoleDO> {
  /**
   * 赋予默认角色给账户
   *
   * @param accountId 账户Id
   * @return 影响行数
   */
  int saveAsDefaultRole(@Param("accountId") Long accountId);

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
  int savePermissions(Long roleId,List<Long> permissions);

  /**
   * 删除中间表信息
   * @param roleId
   * @param permissions
   */
  int deleteRolePermission(Long roleId,List<Long> permissions);
  void deleteRolePermissionItem(Long roleId,Long permissionId);

  /**
   * 获取所有权限表中的字段
   * @param roleId
   * @return List<RolePermissionDO>
   */
  List<RolePermissionDO> getAllRolePermissionTableRow(Long roleId);
}
