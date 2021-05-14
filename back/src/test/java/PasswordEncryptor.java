import com.msy.plus.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author MoShuying
 * @date 2018/05/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PasswordEncryptor {

  @Autowired private PasswordEncoder passwordEncoder;

  @Test
  public void encode() throws Exception {
    final String admin = this.passwordEncoder.encode("root");
    final String user = this.passwordEncoder.encode("123456");
    System.err.println("admin password = " + admin);
    System.err.println("user password = " + user);
  }
}
//  MyEnc({cSs3wYoZ0BTijYqdYVj9xg==})