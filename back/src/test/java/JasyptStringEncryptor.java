import com.msy.plus.Application;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * jasypt 用于加密配置文件 https://github.com/ulisesbocchio/jasypt-spring-boot
 *
 * @author MoShuying
 * @date 2018/05/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JasyptStringEncryptor {

  @Qualifier("myStringEncryptor")
  @Autowired
  private StringEncryptor stringEncryptor;

  @Test
  public void encode() throws Exception {
    final String mysql = this.stringEncryptor.encrypt("com.mysql.cj.jdbc.Driver");
    final String name = this.stringEncryptor.encrypt("crm3");
    final String password = this.stringEncryptor.encrypt("123456");

    System.err.println("name = " + name);
    System.err.println("mysql = " + mysql);
    System.err.println("password = " + password);
  }
}
