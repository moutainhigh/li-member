/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.member.updateAmount;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UpdateBalanceRequest")
public class UpdateBalanceRequest {

  /**
   * 操作类型
   */
  @NotNull(message = "增加或者减少类型")
  @ApiModelProperty(value = "增加或者减小类型，增加为:add，减少为:reduce",required = true)
  private String operateType ;

  /**
   * 金额
   */
  @NotNull(message = "金额不能为空")
  @ApiModelProperty(value = "金额",required = true)
  private  BigDecimal amount = new BigDecimal("0.0");
}
