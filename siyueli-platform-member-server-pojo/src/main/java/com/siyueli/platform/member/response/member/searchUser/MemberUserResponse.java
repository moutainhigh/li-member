/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.response.member.searchUser;

import com.siyueli.platform.member.common.member.MemberUserVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberUserResponse extends MemberUserVo{

  @ApiModelProperty(value = "id")
  private Long id;
}
