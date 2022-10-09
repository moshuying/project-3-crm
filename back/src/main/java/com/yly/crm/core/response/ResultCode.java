package com.yly.crm.core.response;

/**
 * 响应状态码枚举类
 *
 * <p>自定义业务异常 2*** 开始
 *
 * <p>原有类异常 4*** 开始
 *
 * @author MoShuying
 * @date 2018/07/14
 */
public enum ResultCode {
  /** 成功请求，但结果不是期望的成功结果 */
  SUCCEED_REQUEST_FAILED_RESULT(1000, "成功请求，但结果不是期望的成功结果"),

  /** 查询失败 */
  FIND_FAILED(2000, "查询失败"),

  /** 保存失败 */
  SAVE_FAILED(2001, "保存失败"),

  /** 更新失败 */
  UPDATE_FAILED(2002, "更新失败"),

  /** 删除失败 */
  DELETE_FAILED(2003, "删除失败"),

  /** 账户名重复 */
  DUPLICATE_NAME(2004, "账户名重复"),

  /** 数据库异常 */
  DATABASE_EXCEPTION(4001, "数据库异常"),

  /** 认证异常 */
  UNAUTHORIZED_EXCEPTION(4002, "认证异常"),

  /** 验证异常 */
  VIOLATION_EXCEPTION(4003, "验证异常");

  private final int value;

  private final String reason;

  ResultCode(final int value, final String reason) {
    this.value = value;
    this.reason = reason;
  }

  public int getValue() {
    return this.value;
  }

  public String getReason() {
    return this.reason;
  }

  public String format(final Object... objects) {
    return objects.length > 0 ? String.format(this.getReason(), objects) : this.getReason();
  }
}
