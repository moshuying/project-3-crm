package com.yly.crm.service.impl;

import com.yly.crm.core.exception.UsernameNotFoundException2;
import com.yly.crm.entity.AccountWithRoleDO;
import com.yly.crm.service.AccountService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author MoShuying
 * @date 2018/05/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {
  @Resource private AccountService accountService;

  @Override
  public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException2 {
    final AccountWithRoleDO account = this.accountService.getByNameWithRole(name);
    Optional.ofNullable(account).orElseThrow(UsernameNotFoundException2::new);
    final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    account
        .getRoles()
        .forEach(roleDO -> authorities.add(new SimpleGrantedAuthority(roleDO.getName())));
    return new org.springframework.security.core.userdetails.User(
        account.getName(), account.getPassword(), authorities);
  }
}
