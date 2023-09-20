package com.yly.crm.core.jasypt;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 自定义被加密值的发现器 默认：ENC(abc) 自定义：MyEnc({abc})
 *
 * <p>https://github.com/ulisesbocchio/jasypt-spring-boot#provide-a-custom-encryptablepropertydetector
 *
 * <p>如果只是单纯想让前后缀不同，可以直接配置前后缀属性：
 *
 * <p>https://github.com/ulisesbocchio/jasypt-spring-boot#provide-a-custom-encrypted-property-prefix-and-suffix
 *
 * <p>jasypt.encryptor.property.prefix=TEST(
 *
 * <p>jasypt.encryptor.property.suffix=)
 *
 * @author MoShuying
 * @date 2018/07/20
 */
@Component
public class MyEncryptablePropertyDetector implements EncryptablePropertyDetector {
  /** 前缀 */
  private static final String PREFIX = "MyEnc({";
  /** 后缀 */
  private static final String SUFFIX = "})";

  @Override
  public boolean isEncrypted(final String property) {
    if (StringUtils.isBlank(property)) {
      return false;
    }
    final String trimmedProperty = property.trim();

    return trimmedProperty.startsWith(MyEncryptablePropertyDetector.PREFIX)
        && trimmedProperty.endsWith(MyEncryptablePropertyDetector.SUFFIX);
  }

  @Override
  public String unwrapEncryptedValue(final String property) {
    return property.substring(
        MyEncryptablePropertyDetector.PREFIX.length(),
        property.length() - MyEncryptablePropertyDetector.SUFFIX.length());
  }
}
