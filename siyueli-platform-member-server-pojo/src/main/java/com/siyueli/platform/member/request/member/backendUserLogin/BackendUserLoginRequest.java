/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.member.backendUserLogin;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 后台登录请求参数
 */
@Data
@ApiModel(value = "BackendUserLoginRequest")
public class BackendUserLoginRequest {

  /**
   * code
   */
  @NotNull(message = "帐号不能为空")
  @ApiModelProperty(value = "code",required = true)
  private String code;

  /**
   * code
   */
  @NotNull(message = "密码不能为空")
  @ApiModelProperty(value = "password",required = true)
  private String password;


}
