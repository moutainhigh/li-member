/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.member.registerUser;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RegisterUserRequest")
public class RegisterUserRequest {

  /**
   * 手机号
   */
  @NotNull(message = "手机号不能为空")
  @ApiModelProperty(value = "手机号",required = true)
  private String cellphone;

  /**
   * openId
   */
  @NotNull(message = "openId不能为空")
  @ApiModelProperty(value = "openId",required = true)
  private String openId;

  /**
   * 手机号
   */
  @NotNull(message = "验证码不能为空")
  @ApiModelProperty(value = "验证码",required = true)
  private String verificateCode;
}
