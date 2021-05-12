import com.zoctan.seedling.core.rsa.RsaUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * RSA工具测试
 *
 * @author Zoctan
 * @date 2018/05/27
 */
public class RsaEncryptor {
  private final RsaUtils rsaUtil = new RsaUtils();

  /** 加载公私钥pem格式文件测试 */
  @Test
  public void test1() throws Exception {
    final PublicKey publicKey = this.rsaUtil.loadPublicKey();
    final PrivateKey privateKey = this.rsaUtil.loadPrivateKey();
    Assert.assertNotNull(publicKey);
    Assert.assertNotNull(privateKey);
    System.out.println("公钥：" + publicKey);
    System.out.println("私钥：" + privateKey);

    final String data = "zoctan";
    // 公钥加密
    final byte[] encrypted = this.rsaUtil.encrypt(data.getBytes());
    System.out.println("加密后：" + Base64Utils.encodeToString(encrypted));

    // 私钥解密
    final byte[] decrypted = this.rsaUtil.decrypt(encrypted);
    System.out.println("解密后：" + new String(decrypted));
  }

  /** 生成RSA密钥对并进行加解密测试 */
  @Test
  public void test2() throws Exception {
    final String data = "hello word";
    final KeyPair keyPair = RsaUtils.genKeyPair(512);

    // 获取公钥，并以base64格式打印出来
    final PublicKey publicKey = keyPair.getPublic();
    System.out.println("公钥：" + new String(Base64.getEncoder().encode(publicKey.getEncoded())));

    // 获取私钥，并以base64格式打印出来
    final PrivateKey privateKey = keyPair.getPrivate();
    System.out.println("私钥：" + new String(Base64.getEncoder().encode(privateKey.getEncoded())));

    // 公钥加密
    final byte[] encrypted = RsaUtils.encrypt(data.getBytes(), publicKey);
    System.out.println("加密后：" + new String(encrypted));

    // 私钥解密
    final byte[] decrypted = RsaUtils.decrypt(encrypted, privateKey);
    System.out.println("解密后：" + new String(decrypted));
  }
}
