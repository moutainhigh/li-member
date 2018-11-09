/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.constants.coupon;

/**
 * 卡券任务状态
 */
public enum CouponTaskStatus {

  NOT_ENABLE(0, "卡券任务未启用"),

  ENABLE(1, "卡券任务启用"),

  FORBIDDEN(2, "卡券任务禁用");

  private Integer value;

  private String description;

  CouponTaskStatus(Integer value, String description) {
    this.value = value;
    this.description = description;
  }

  public Integer getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }

  public static Boolean contains(Integer value) {
    if (value == null) {
      return false;
    }
    for (CouponTaskStatus status : CouponTaskStatus.values()) {
      if (status.getValue().equals(value)) {
        return true;
      }
    }

    return false;
  }
}
