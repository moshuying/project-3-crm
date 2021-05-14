package com.zoctan.seedling.mapper;

import com.zoctan.seedling.core.mapper.MyMapper;
import com.zoctan.seedling.entity.RoleDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Zoctan
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
  RoleDO getDetailById(Long id);
}
