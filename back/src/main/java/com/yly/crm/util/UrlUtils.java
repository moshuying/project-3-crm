package com.yly.crm.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Url工具
 *
 * @author MoShuying
 * @date 2018/07/13
 */
public class UrlUtils {
  private UrlUtils() {}

  /**
   * 请求的相对路径 /user/list
   *
   * @param request request
   * @return 相对路径
   */
  public static String getMappingUrl(final ServletRequest request) {
    return getMappingUrl((HttpServletRequest) request);
  }

  public static String getMappingUrl(final HttpServletRequest request) {
    return request.getRequestURI().substring(request.getContextPath().length());
  }
}
