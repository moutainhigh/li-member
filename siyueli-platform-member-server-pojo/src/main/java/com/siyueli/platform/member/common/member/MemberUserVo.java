/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.common.member;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonSerialize(include= Inclusion.ALWAYS)
@Data
public class MemberUserVo {

  @ApiModelProperty(value = "主健id")
  private Long id;

  /**
   * 会员号
   */
  @ApiModelProperty(value = "会员号")
  private String code;

  /**
   * 会员名称
   */
  @ApiModelProperty(value = "会员名称")
  private String name;

  /**
   * 会员等级
   */
  @ApiModelProperty(value = "会员等级")
  private Long gradeId;

  /**
   * 等级名称
   */
  @ApiModelProperty(value = "等级名称")
  private String gradeName;

  /**
   * 手机号码
   */
  @ApiModelProperty(value = "手机号码")
  private String cellphone;

  /**
   * 注册渠道
   */
  @ApiModelProperty(value = "注册渠道")
  private String registerChannel;
  /**
   * 微信unnitId
   */
  @ApiModelProperty(value = "微信unnitId")
  private String unnitId;
  /**
   * 车牌号
   */
  @ApiModelProperty(value = "车牌号")
  private String carNum;
  /**
   * 生日
   */
  @ApiModelProperty(value = "生日")
  private LocalDateTime birthday;

  /**
   * 身份证号码
   */
  @ApiModelProperty(value = "身份证号码")
  private String identityId;

  /**
   * 邮箱
   */
  @ApiModelProperty(value = "邮箱")
  private String email;

  /**
   * 密码
   */
  @ApiModelProperty(value = "密码")
  private String password;

  /**
   * 状态
   */
  @ApiModelProperty(value = "状态")
  private Integer status;

  @ApiModelProperty(value = "验证码")
  private String verificateCode;


  /**
   * 积分
   */
  @ApiModelProperty(value = "积分")
  private BigDecimal points = new BigDecimal("0.0");

  /**
   * 余额
   */
  @ApiModelProperty(value = "余额")
  private BigDecimal balance = new BigDecimal("0.0");

  /**
   * 赠送余额
   */
  @ApiModelProperty(value = "赠送余额")
  private BigDecimal donateBalance= new BigDecimal("0.0");

  /**
   * 压金
   */
  @ApiModelProperty(value = "压金")
  private BigDecimal deposite = new BigDecimal("0.0");

  /**
   * 注册时间
   */
  @ApiModelProperty(value = "注册时间")
  private LocalDateTime createAt;

  /**
   * 更新时间
   */
  @ApiModelProperty(value = "更新时间")
  private LocalDateTime updateAt;
}
