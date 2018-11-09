package com.siyueli.platform.member.request.member.amount;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
@Data
@ApiModel(value = "SearchAmountRequest")
public class SearchAmountRequest {
  /**
   * 操作类型
   */
  @ApiModelProperty(value = "页码")
  private int page = 1;

  /**
   * 类型
   */
  @ApiModelProperty(value = "大小")
  private int  size = 15;


  /**
   * 类型
   */
  @ApiModelProperty(value = "类型。积分：0，余额：1",required = true)
  private int type;

}
