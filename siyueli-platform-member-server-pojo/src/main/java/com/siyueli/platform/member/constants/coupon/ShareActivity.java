/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.constants.coupon;

public enum ShareActivity {

  NOT_ENABLE(0, "不与其他优惠活动共享"),
  ENABLE(1, "可与其他优惠活动共享");

  private Integer value;

  private String decription;

  ShareActivity(Integer value, String decription) {
    this.value = value;
    this.decription = decription;
  }

  public Integer getValue() {
    return value;
  }

  public String getDecription() {
    return decription;
  }
}
