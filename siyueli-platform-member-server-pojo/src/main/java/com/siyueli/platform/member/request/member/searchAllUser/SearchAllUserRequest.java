/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.member.searchAllUser;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SearchAllUserRequest")
public class SearchAllUserRequest {

  /**
   * 会员号
   */
  @ApiModelProperty(value = "会员号")
  private String code;

  /**
   * 手机号
   */
  @ApiModelProperty(value = "手机号")
  private String cellphone;

  /**
   * 等级id
   */
  @ApiModelProperty(value = "等级id")
  private Long gradeId;

  /**
   * 注册时间
   */
  @ApiModelProperty(value = "注册时间")
  private String registerTime;
}
