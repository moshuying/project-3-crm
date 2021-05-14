package com.msy.plus.util;

import com.alibaba.fastjson.JSONObject;
import com.msy.plus.entity.AccountDO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JsonUtilsTest {
  private List<AccountDO> getAccountList() {
    final AccountDO account1 =
        new AccountDO() {
          {
            this.setName("ll");
            this.setId(1L);
            this.setEmail("ll@qq.com");
            this.setPassword("llllllllllll");
          }
        };

    final AccountDO account2 =
        new AccountDO() {
          {
            this.setName("aa");
            this.setId(2L);
            this.setEmail("aa@qq.com");
            this.setPassword("aaaaaaaaaa");
          }
        };
    return Arrays.asList(account1, account2);
  }

  @Test
  public void keepFields() throws Exception {
    final List<JSONObject> accountList =
        JsonUtils.keepFields(this.getAccountList(), List.class, "password");
    for (final JSONObject account : accountList) {
      assert account.get("id") == null;
      assert account.get("password") != null;
    }
  }

  @Test
  public void deleteFields() throws Exception {
    final List<JSONObject> accountList =
        JsonUtils.deleteFields(this.getAccountList(), List.class, "password");
    for (final JSONObject account : accountList) {
      assert account.get("id") != null;
      assert account.get("password") == null;
    }
  }
}
