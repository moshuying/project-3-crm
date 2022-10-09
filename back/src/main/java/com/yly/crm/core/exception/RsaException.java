package com.yly.crm.core.exception;

/**
 * Rsa 异常
 *
 * @author MoShuying
 * @date 2018/05/27
 */
public class RsaException extends RuntimeException {
  private static final long serialVersionUID = 5010582133829256626L;

  private static final String DEFAULT_MESSAGE = "Rsa 异常";

  public RsaException() {
    super(RsaException.DEFAULT_MESSAGE);
  }

  public RsaException(final String message) {
    super(message);
  }

  public RsaException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
