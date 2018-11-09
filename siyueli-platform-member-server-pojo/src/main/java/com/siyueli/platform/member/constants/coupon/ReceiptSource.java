/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.constants.coupon;

public enum ReceiptSource {

  /**
   * 领取来源平台，就是说卡券在某个平台才能领取
   */
  ALL_PLATFORM(0, "all_platform", "全平台"),
  MINI_PROGRAM(9, "mini_platform", "微信小程序");

  /**
   * 定义卡券source来源
   */
  private Integer value;

  /**
   * Identity值
   */
  private String identity;

  /**
   * 描述
   */
  private String description;

  ReceiptSource(Integer value, String identity, String description) {
    this.value = value;
    this.identity = identity;
    this.description = description;
  }

  public Integer getValue() {
    return value;
  }

  public String getIdentity() {
    return identity;
  }

  public String getDescription() {
    return description;
  }
}
