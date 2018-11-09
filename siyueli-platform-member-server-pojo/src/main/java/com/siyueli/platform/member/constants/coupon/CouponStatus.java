/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.constants.coupon;

/**
 * 优惠券状态
 */
public enum CouponStatus {

  WAITING_TAKE(0, "等待领取"),
  TAKEN(1,"已被领取 等待核销"),
  USED(2,"已被使用 已核销"),
  EXPIRE(3, "已过期");

  private Integer value;

  private String description;

  CouponStatus(Integer value, String description) {
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
