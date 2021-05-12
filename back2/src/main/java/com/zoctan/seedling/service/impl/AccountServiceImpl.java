package com.zoctan.seedling.service.impl;

import com.zoctan.seedling.core.response.ResultCode;
import com.zoctan.seedling.core.service.AbstractService;
import com.zoctan.seedling.dto.AccountDTO;
import com.zoctan.seedling.entity.AccountDO;
import com.zoctan.seedling.entity.AccountWithRoleDO;
import com.zoctan.seedling.mapper.AccountMapper;
import com.zoctan.seedling.query.AccountQuery;
import com.zoctan.seedling.service.AccountService;
import com.zoctan.seedling.service.RoleService;
import com.zoctan.seedling.util.AssertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Zoctan
 * @date 2018/05/27
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends AbstractService<AccountDO> implements AccountService {
  @Resource private AccountMapper accountMapper;
  @Resource private RoleService roleService;
  @Resource private PasswordEncoder passwordEncoder;

  /** 重写 save 方法，密码加密后再存，并且赋予默认角色 */
  @Override
  public void save(final AccountDTO accountDTO) {
    AssertUtils.asserts(
        !Optional.ofNullable(this.getBy("name", accountDTO.getName())).isPresent(),
        ResultCode.DUPLICATE_NAME);

    final AccountDO accountDO = accountDTO.convertToDO();

    accountDO.setPassword(this.passwordEncoder.encode(accountDTO.getPassword().trim()));
    this.save(accountDO);
    log.debug("==> Create Account<{}> Id<{}>", accountDO.getName(), accountDO.getId());
    // 新建账户默认角色
    this.roleService.saveAsDefaultRole(accountDO.getId());
  }

  @Override
  public void updateByName(final AccountDTO accountDTO) {
    final AccountDO accountDO = accountDTO.convertToDO();
    // 如果修改了密码
    if (StringUtils.isNotBlank(accountDTO.getPassword())) {
      // 密码修改后需要加密
      accountDO.setPassword(this.passwordEncoder.encode(accountDTO.getPassword().trim()));
    }
    // 不能修改账户名
    final String name = accountDO.getName();
    accountDO.setName(null);
    // 按 name 字段更新
    final Condition condition = new Condition(AccountDO.class);
    condition.createCriteria().andCondition("name = ", name);
    this.updateByCondition(accountDO, condition);
  }

  @Override
  public AccountWithRoleDO getByIdWithRole(final Long id) {
    final AccountQuery accountQuery = AccountQuery.builder().id(id).build();
    return this.accountMapper.getByQueryWithRole(accountQuery);
  }

  @Override
  public AccountWithRoleDO getByNameWithRole(final String name) {
    final AccountQuery accountQuery = AccountQuery.builder().name(name).build();
    return this.accountMapper.getByQueryWithRole(accountQuery);
  }

  @Override
  public boolean verifyPassword(final String rawPassword, final String encodedPassword) {
    return this.passwordEncoder.matches(rawPassword, encodedPassword);
  }

  @Override
  public boolean updateLoginTimeByName(final String name) {
    final boolean success = this.accountMapper.updateLoginTimeByName(name) == 1;
    if (!success) {
      log.error("==> Update Account<{}> login time error", name);
    }
    return success;
  }
}
