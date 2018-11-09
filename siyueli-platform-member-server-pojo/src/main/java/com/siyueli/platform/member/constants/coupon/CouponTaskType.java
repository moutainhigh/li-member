/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.constants.coupon;

/**
 * 卡券类型
 */
public enum CouponTaskType {
  CASH(0, "代金券"),
  DISCOUNT(1, "打折券"),
  GIFT(2, "赠品券");

  private Integer value;
  private String description;

  CouponTaskType(Integer value, String description) {
    this.value = value;
    this.description = description;
  }

  public Integer getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }
}
