package com.msy.plus.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Test {
  /** 测试Id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 测试名称 */
  private String name;

  /**
   * 获取测试Id
   *
   * @return id - 测试Id
   */
  public Long getId() {
    return id;
  }

  /**
   * 设置测试Id
   *
   * @param id 测试Id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 获取测试名称
   *
   * @return name - 测试名称
   */
  public String getName() {
    return name;
  }

  /**
   * 设置测试名称
   *
   * @param name 测试名称
   */
  public void setName(String name) {
    this.name = name;
  }
}
