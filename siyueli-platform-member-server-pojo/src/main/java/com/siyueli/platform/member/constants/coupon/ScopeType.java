/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.constants.coupon;

/**
 * 适用范围
 */
public enum ScopeType {

  ALL(0, "全场通用"),
  AVAILABLE_SKU(1,"指定范围：可用商品"),
  NOT_AVAILABLE_SKU(2, "指定范围：不可用商品");

  private Integer value;

  private String description;

  ScopeType(Integer value, String description) {
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
