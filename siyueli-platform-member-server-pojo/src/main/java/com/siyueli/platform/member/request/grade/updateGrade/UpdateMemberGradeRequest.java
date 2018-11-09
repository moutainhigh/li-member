/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.grade.updateGrade;

import com.siyueli.platform.member.common.grade.MemberGradeVo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改等级请求
 */
@Data
@ApiModel(value = "UpdateMemberGradeRequest")
public class UpdateMemberGradeRequest extends MemberGradeVo{

  @NotNull(message = "图片密钥不能为空")
  @ApiModelProperty(value = "图片密钥",required = true)
  private String imgSecret;
}
