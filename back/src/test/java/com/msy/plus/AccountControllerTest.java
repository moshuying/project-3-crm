package com.msy.plus;

import com.msy.plus.dto.AccountDTO;
import com.msy.plus.dto.AccountLoginDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 账户接口测试
 *
 * @author MoShuying
 * @date 2018/11/29
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountControllerTest extends BaseControllerTest {

  private final String resource = "/account";

  /** register */
  @Test(timeout = 5000)
  public void test1() throws Exception {
    final String targetUrl = this.resource;
    final AccountDTO account = new AccountDTO();
    account.setEmail("12345@qq.com");
    account.setName("xxxxx");
    account.setPassword("12345");
    this.post(targetUrl, account, null);
  }

  /** login */
  @Test(timeout = 5000)
  public void test2() throws Exception {
    final String targetUrl = this.resource + "/token";
    final AccountLoginDTO accountLogin = new AccountLoginDTO();
    accountLogin.setName("xxxxx");
    accountLogin.setPassword("12345");
    this.post(targetUrl, accountLogin, null);
  }

  /** logout */
  @Test(timeout = 5000)
  public void test3() throws Exception {
    final String targetUrl = this.resource + "/token";
    final AccountLoginDTO accountLogin = new AccountLoginDTO();
    accountLogin.setName("admin");
    accountLogin.setPassword("admin");
    // 先登录获取token
    final String token = (String) this.post(targetUrl, accountLogin, null).getData();
    this.delete(targetUrl, null, token);
  }

  /** update */
  @Test(timeout = 5000)
  @WithCustomUser(name = "user")
  public void test4() throws Exception {
    final String targetUrl = this.resource;
    final AccountDTO accountDTO = new AccountDTO();
    accountDTO.setName("user");
    accountDTO.setEmail("xxxxx@qq.com");
    this.patch(targetUrl, accountDTO, null);
  }

  /** detail */
  @Test(timeout = 5000)
  @WithCustomUser(name = "xxxxx")
  public void test5() throws Exception {
    final String targetUrl = this.resource + "/3";
    this.get(targetUrl, null, null);
  }

  /** list */
  @Test(timeout = 5000)
  @WithCustomUser(name = "user")
  public void test6() throws Exception {
    final String targetUrl = this.resource + "?page=1&size=3";
    this.get(targetUrl, null, null);
  }

  /** delete */
  @Test(timeout = 5000)
  @WithCustomUser(name = "admin")
  public void test7() throws Exception {
    final String targetUrl = this.resource + "/3";
    this.delete(targetUrl, null, null);
  }
}
