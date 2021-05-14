package com.msy.plus.util;

import com.msy.plus.core.exception.ServiceException;
import com.msy.plus.core.response.ResultCode;

/**
 * 断言工具
 *
 * @author MoShuying
 * @date 2018/11/29
 */
public class AssertUtils {
  public static void throwIf(
      final boolean statement, final ResultCode resultCode, final String message) {
    if (statement) {
      throw toThrow(resultCode, message);
    }
  }

  public static void throwIf(
      final boolean statement, final ResultCode resultCode, final Object... messages) {
    throwIf(statement, resultCode, resultCode.format(messages));
  }

  public static RuntimeException toThrow(final ResultCode resultCode, final Object... messages) {
    return new ServiceException(resultCode, resultCode.format(messages));
  }

  public static void asserts(
      final boolean statement, final ResultCode resultCode, final Object... messages) {
    throwIf(!statement, resultCode, messages);
  }
}
