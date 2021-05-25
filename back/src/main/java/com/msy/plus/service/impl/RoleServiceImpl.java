package com.msy.plus.service.impl;

import com.msy.plus.core.response.ResultCode;
import com.msy.plus.core.service.AbstractService;
import com.msy.plus.dto.RoleDTO;
import com.msy.plus.entity.RoleDO;
import com.msy.plus.entity.RolePermissionDO;
import com.msy.plus.entity.RoleWithPermissionDO;
import com.msy.plus.mapper.RoleMapper;
import com.msy.plus.service.RoleService;
import com.msy.plus.util.AssertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MoShuying
 * @date 2018/05/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends AbstractService<RoleDO> implements RoleService {
  @Resource private RoleMapper roleMapper;

  @Override
  public void saveAsDefaultRole(final Long accountId) {
    final boolean success = this.roleMapper.saveAsDefaultRole(accountId) == 1;
    AssertUtils.asserts(success, ResultCode.SAVE_FAILED, "账户默认角色保存失败");
  }

  @Override
  public void save(final RoleDTO roleDTO) {
    final RoleDO role = roleDTO.convertToDO();
    this.save(role);
  }

  @Override
  public void update(final RoleDTO roleDTO) {
    final RoleDO role = roleDTO.convertToDO();
    this.update(role);
  }

  @Override
  public RoleWithPermissionDO getDetailById(Long id) {
    return this.roleMapper.getDetailById(id);
  }

  @Override
  public void savePermissions(Long roleId,List<Long> permissions) {
    this.roleMapper.savePermissions(roleId,permissions);
//    AssertUtils.asserts(success, ResultCode.SAVE_FAILED, "账户角色权限保存失败");
  }

  @Override
  public void deleteRolePermissionItem(Long roleId, Long permissionId) {
    this.roleMapper.deleteRolePermissionItem(roleId,permissionId);
  }

  @Override
  public List<RolePermissionDO> getAllRolePermissionTableRow(Long roleId) {
    return this.roleMapper.getAllRolePermissionTableRow(roleId);
  }
}
