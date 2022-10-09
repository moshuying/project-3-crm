package com.yly.crm.core.exception;

/**
 * Yml配置没找到异常
 *
 * @author MoShuying
 * @date 2020/11/12
 */
public class YamlNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 7081775224645592074L;

  private static final String DEFAULT_MESSAGE = "Yml不存在";

  public YamlNotFoundException() {
    super(YamlNotFoundException.DEFAULT_MESSAGE);
  }

  public YamlNotFoundException(final String message) {
    super(message);
  }

  public YamlNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
