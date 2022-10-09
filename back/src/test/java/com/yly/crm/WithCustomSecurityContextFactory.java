package com.yly.crm;

import com.yly.crm.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import javax.annotation.Resource;

/**
 * 设置用户登陆时的 SecurityContext
 *
 * @author MoShuying
 * @date 2018/11/29
 */
public class WithCustomSecurityContextFactory
    implements WithSecurityContextFactory<WithCustomUser> {
  @Resource private UserDetailsServiceImpl userDetailsService;

  @Override
  public SecurityContext createSecurityContext(final WithCustomUser customUser) {
    final SecurityContext context = SecurityContextHolder.createEmptyContext();
    final UserDetails userDetails = this.userDetailsService.loadUserByUsername(customUser.name());
    final Authentication auth =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    context.setAuthentication(auth);
    return context;
  }
}
