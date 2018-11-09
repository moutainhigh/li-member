/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.response.rights.common;

import com.siyueli.platform.member.common.rights.MemberRightsVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberRightsResponse extends MemberRightsVo {

  @ApiModelProperty(value = "权益id")
  private String id;
}
