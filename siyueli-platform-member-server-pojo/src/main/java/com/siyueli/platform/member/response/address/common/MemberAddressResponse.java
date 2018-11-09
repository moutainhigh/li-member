/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.response.address.common;

import com.siyueli.platform.member.common.address.MemberAddressVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberAddressResponse extends MemberAddressVo{

  @ApiModelProperty(value = "地址id")
  private Long id;
}
