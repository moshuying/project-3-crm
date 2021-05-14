package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.RoleDO;
import com.msy.plus.entity.RoleWithPermissionDO;
import org.apache.ibatis.annotations.Param;

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
  RoleWithPermissionDO getDetailById(Long id);
}
