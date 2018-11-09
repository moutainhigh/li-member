/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.constants.coupon;

public enum ShareClubCard {

  NOT_ENABLE(0, "不与会员卡共享"),
  ENABLE(1, "可与会员卡共享");

  private Integer value;

  private String decription;

  ShareClubCard(Integer value, String decription) {
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
