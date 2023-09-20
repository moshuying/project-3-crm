package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.core.response.ResultCode;
import com.yly.crm.dto.RoleDTO;
import com.yly.crm.entity.RoleDO;
import com.yly.crm.entity.RolePermissionDO;
import com.yly.crm.entity.RoleWithPermissionDO;
import com.yly.crm.mapper.RoleMapper;
import com.yly.crm.service.RoleService;
import com.yly.crm.util.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

  @Autowired
  private RoleMapper roleMapper;

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
    this.saveOrUpdate(role);
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
