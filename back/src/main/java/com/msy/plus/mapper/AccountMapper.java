package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msy.plus.entity.AccountDO;
import com.msy.plus.entity.AccountWithRoleDO;
import com.msy.plus.query.AccountQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author MoShuying
 * @date 2018/05/27
 */

@Component
public interface AccountMapper extends BaseMapper<AccountDO> {
  /**
   * 按条件查询账户
   *
   * @param accountQuery 账户查询条件
   * @return 账户
   */
  AccountWithRoleDO getByQueryWithRole(AccountQuery accountQuery);

  /**
   * 按账户名更新最后登陆时间
   *
   * @param name 账户名
   * @return 影响行数
   */
  int updateLoginTimeByName(@Param("name") String name);
}
