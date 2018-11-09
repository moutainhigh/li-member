/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.coupon;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "卡券查询请求(CouponTaskListRequest)")
public class CouponTaskListRequest {

  /**
   * 卡券名称
   */
  @ApiModelProperty(value = "卡券名称")
  private String name;

  /**
   * 卡券类型
   */
  @ApiModelProperty(value = "卡券类型 0：代金券，1：打折券，2:赠品券")
  @Range(min = 0, max = 2, message = "适用范围在0-2")
  private Integer typeId;
}
