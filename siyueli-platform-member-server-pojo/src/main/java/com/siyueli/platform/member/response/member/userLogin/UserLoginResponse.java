/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.response.member.userLogin;

import com.siyueli.platform.member.common.member.MemberUserVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginResponse extends MemberUserVo{

  @ApiModelProperty(value = "id")
  private Long id;
}
