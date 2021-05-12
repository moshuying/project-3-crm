package com.zoctan.seedling.core.rsa;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RSA 配置
 *
 * @author Zoctan
 * @date 2019/08/12
 */
@Data
@Component
@ConfigurationProperties(prefix = "rsa")
public class RsaConfigurationProperties {
  /** 公钥头 */
  private String publicKeyHead = "-----BEGIN PUBLIC KEY-----";

  /** 公钥尾 */
  private String publicKeyTail = "-----END PUBLIC KEY-----";

  /** 私钥头 */
  private String privateKeyHead = "-----BEGIN PRIVATE KEY-----";

  /** 私钥尾 */
  private String privateKeyTail = "-----END PRIVATE KEY-----";

  /** 公钥位置，默认在 rsa 文件夹下 */
  private String publicKeyPath = "classpath:rsa/public-key.pem";

  /** 私钥位置，默认在 rsa 文件夹下 */
  private String privateKeyPath = "classpath:rsa/private-key.pem";

  /** 使用文件还是直接使用字符串，默认使用字符串 */
  private boolean useFile = false;

  /** 私钥 */
  private String privateKey;

  /** 公钥 */
  private String publicKey;
}
