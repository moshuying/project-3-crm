package com.yly.crm.core.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Json web token 配置
 *
 * @author MoShuying
 * @date 2018/06/09
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigurationProperties {
  /** claim authorities key */
  private String claimKeyAuth;

  /** token 前缀 */
  private String tokenType;

  /** 请求头或请求参数的key */
  private String header;

  /** 有效期 */
  private Duration expireTime;
}
