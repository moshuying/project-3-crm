package com.zoctan.seedling.core.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户名没找到异常
 *
 * @author Zoctan
 * @date 2020/11/12
 */
public class UsernameNotFoundException2 extends UsernameNotFoundException {
  private static final long serialVersionUID = 90476943748478489L;

  private static final String DEFAULT_MESSAGE = "账户名不存在";

  public UsernameNotFoundException2() {
    super(UsernameNotFoundException2.DEFAULT_MESSAGE);
  }

  public UsernameNotFoundException2(final String message) {
    super(message);
  }

  public UsernameNotFoundException2(final String message, final Throwable cause) {
    super(message, cause);
  }
}
