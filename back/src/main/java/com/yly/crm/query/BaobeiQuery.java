package com.yly.crm.query;

import lombok.Builder;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户查询实体
 *
 * @author jiuyuehe
 * @date 2022/07/15
 */
@Builder
public class BaobeiQuery implements Serializable {
  private static final long serialVersionUID = 4063412382769589319L;


  /** 客户名称关键字 */
  private final String customerNameKeyword;

  /** 用户名关键字 */
  private final String userNameKeyword;

  /** 开始时间 */
  private final Date startTime;

  /** 结束时间 */
  private final Date endTime;
}
