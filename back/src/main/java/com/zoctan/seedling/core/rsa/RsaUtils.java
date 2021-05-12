package com.zoctan.seedling.core.rsa;

import com.zoctan.seedling.core.exception.RsaException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Optional;

/**
 * RSA 工具
 *
 * <p>用openssl生成512位RSA：
 *
 * <p>生成私钥： openssl genrsa -out key.pem 512
 *
 * <p>从私钥中导出公钥： openssl rsa -in key.pem -pubout -out public-key.pem
 *
 * <p>公钥加密： openssl rsautl -encrypt -in xx.file -inkey public-key.pem -pubin -out xx.en
 *
 * <p>私钥解密： openssl rsautl -decrypt -in xx.en -inkey key.pem -out xx.de
 *
 * <p>pkcs8编码（Java）： openssl pkcs8 -topk8 -inform PEM -in key.pem -outform PEM -out private-key.pem
 * -nocrypt
 *
 * <p>最后将公私玥放在/resources/rsa/：private-key.pem public-key.pem
 *
 * @author Zoctan
 * @date 2018/07/20
 */
@Slf4j
@Component
public class RsaUtils {
  private static final String ALGORITHM = "RSA";
  private final ResourceLoader resourceLoader = new DefaultResourceLoader();
  @javax.annotation.Resource private RsaConfigurationProperties rsaProperties;

  public RsaUtils() {
    if (!Optional.ofNullable(this.rsaProperties).isPresent()) {
      this.rsaProperties = new RsaConfigurationProperties();
    }
  }

  /**
   * 生成密钥对
   *
   * @param keyLength 密钥长度(最少512位)
   * @return 密钥对 公钥 keyPair.getPublic() 私钥 keyPair.getPrivate()
   * @throws Exception e
   */
  public static KeyPair genKeyPair(final int keyLength) throws Exception {
    final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RsaUtils.ALGORITHM);
    keyPairGenerator.initialize(keyLength);
    return keyPairGenerator.generateKeyPair();
  }

  /**
   * 公钥加密
   *
   * @param content 待加密数据
   * @param publicKey 公钥
   * @return 加密内容
   * @throws Exception e
   */
  public static byte[] encrypt(final byte[] content, final PublicKey publicKey) throws Exception {
    final Cipher cipher = Cipher.getInstance(RsaUtils.ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(content);
  }

  /**
   * 私钥解密
   *
   * @param content 加密数据
   * @param privateKey 私钥
   * @return 解密内容
   * @throws Exception e
   */
  public static byte[] decrypt(final byte[] content, final PrivateKey privateKey) throws Exception {
    final Cipher cipher = Cipher.getInstance(RsaUtils.ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return cipher.doFinal(content);
  }

  /**
   * 公钥加密
   *
   * @param content 待加密数据
   * @return 加密内容
   * @throws Exception e
   */
  public byte[] encrypt(final byte[] content) throws Exception {
    return RsaUtils.encrypt(content, this.loadPublicKey());
  }

  /**
   * 私钥解密
   *
   * @param content 加密数据
   * @return 解密内容
   * @throws Exception e
   */
  public byte[] decrypt(final byte[] content) throws Exception {
    return RsaUtils.decrypt(content, this.loadPrivateKey());
  }
  /**
   * 加载pem格式X509编码的公钥
   *
   * @return 公钥
   */
  public PublicKey loadPublicKey() throws RsaException {
    final byte[] decoded;
    if (this.rsaProperties.isUseFile()) {
      decoded =
          this.loadAndReplaceAndDecodeByBase64(
              this.rsaProperties.getPublicKeyPath(),
              this.rsaProperties.getPublicKeyHead(),
              this.rsaProperties.getPublicKeyTail());
    } else {
      decoded = Base64.decodeBase64(this.rsaProperties.getPublicKey());
    }
    final X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
    try {
      final KeyFactory keyFactory = KeyFactory.getInstance(RsaUtils.ALGORITHM);
      return keyFactory.generatePublic(spec);
    } catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
      RsaUtils.log.error("==> {}", e.getMessage());
      return null;
    }
  }

  /**
   * 加载pem格式PKCS8编码的私钥
   *
   * @return 私钥
   */
  public PrivateKey loadPrivateKey() throws RsaException {
    final byte[] decoded;
    if (this.rsaProperties.isUseFile()) {
      decoded =
          this.loadAndReplaceAndDecodeByBase64(
              this.rsaProperties.getPrivateKeyPath(),
              this.rsaProperties.getPrivateKeyHead(),
              this.rsaProperties.getPrivateKeyTail());
    } else {
      decoded = Base64.decodeBase64(this.rsaProperties.getPrivateKey());
    }
    final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
    try {
      final KeyFactory keyFactory = KeyFactory.getInstance(RsaUtils.ALGORITHM);
      return keyFactory.generatePrivate(spec);
    } catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
      RsaUtils.log.error("==> {}", e.getMessage());
      return null;
    }
  }

  /**
   * 加载文件后替换头和尾并解密
   *
   * @return 文件字节
   */
  private byte[] loadAndReplaceAndDecodeByBase64(
      final String keyPath, final String headReplace, final String tailReplace)
      throws RsaException {
    final Resource resource =
        Optional.ofNullable(keyPath).map(this.resourceLoader::getResource).get();
    if (!resource.exists()) {
      throw new RsaException("==> 密钥不存在");
    }
    try {
      final String key = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
      return Base64.decodeBase64(
          key.replace(headReplace, "").trim().replace(tailReplace, "").trim());
    } catch (final IOException e) {
      throw new RsaException("==> 密钥读取异常: {}", e);
    }
  }
}
