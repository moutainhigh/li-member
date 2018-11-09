/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.response.grade.common;

import com.siyueli.platform.member.common.grade.MemberGradeVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberGradeResponse extends MemberGradeVo {

  @ApiModelProperty(value = "等级id")
  private Long id;
}
