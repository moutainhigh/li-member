/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.member.userLogin;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserLoginRequest")
public class UserLoginRequest {

  /**
   * jsCode
   */
  @NotNull(message = "jsCode不能为空")
  @ApiModelProperty(value = "jsCode",required = true)
  private String jsCode;


}
