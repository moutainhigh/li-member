/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.constants.coupon;

public enum ChannelType {
  ALL(0, "全渠道"),
  ONLINE(1, "广州店"),
  OFFLINE(2, "上海店");

  private Integer value;

  private String description;

  ChannelType(Integer value, String description) {
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
    for (ChannelType status : ChannelType.values()) {
      if (status.getValue().equals(value)) {
        return true;
      }
    }

    return false;
  }

}
