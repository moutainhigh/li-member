/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.common.grade;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonSerialize(include= Inclusion.ALWAYS)
@Data
public class MemberGradeVo {


  /**
   * 等级名称
   */
  @NotNull(message = "等级名称不能为空")
  @ApiModelProperty(value = "等级名称",required = true)
  private String name;
  /**
   * 背影图地址
   */
  private String imgUrl;
  /**
   * 晋升门槛
   */
  @ApiModelProperty(value = "晋升门槛")
  private BigDecimal promoteCondition;

  @ApiModelProperty(value = "权益ids")
  private List<Long> rightsId;
}
