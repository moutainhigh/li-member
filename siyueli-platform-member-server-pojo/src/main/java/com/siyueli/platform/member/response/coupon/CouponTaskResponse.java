/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.response.coupon;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "卡券响应(CouponTaskResponse)")
public class CouponTaskResponse {

  @ApiModelProperty(value = "id")
  private Long id;

  /**
   * 卡券名称
   */
  @ApiModelProperty(value = "卡券名称")
  private String name;
  /**
   * 卡券类型
   */
  @ApiModelProperty(value = "卡券类型")
  private Integer typeId;
  /**
   * 卡券状态
   */
  @ApiModelProperty(value = "卡券状态")
  private Integer statusId;
  /**
   * 领取开始时间
   */
  @ApiModelProperty(value = "领取开始时间")
  private LocalDateTime startAt;
  /**
   * 领取结束时间
   */
  @ApiModelProperty(value = "领取结束时间")
  private LocalDateTime endAt;
  /**
   * 有效开始时间
   */
  @ApiModelProperty(value = "有效开始时间")
  private LocalDateTime validTimeStartAt;
  /**
   * 有效结束时间
   */
  @ApiModelProperty(value = "有效结束时间")
  private LocalDateTime validTimeEndAt;
  /**
   * 从第几天起有效，0表示当天有效
   */
  @ApiModelProperty(value = "从第几天起有效，0表示当天有效")
  private Integer receiptStartDay;
  /**
   * 有效后结束天数
   */
  @ApiModelProperty(value = "有效后结束天数")
  private Integer receiptEndDay;
  /**
   * 限领张数
   */
  @ApiModelProperty(value = "限领张数")
  private Integer receiptLimit;
  /**
   * 库存数
   */
  @ApiModelProperty(value = "库存数")
  private Integer stockQty;
  /**
   * 是否与其他优惠活动共享
   */
  @ApiModelProperty(value = "是否与其他优惠活动共享")
  private Integer shareActivity;
  /**
   * 是否与会员卡优惠共享
   */
  @ApiModelProperty(value = "是否与会员卡优惠共享")
  private Integer shareClubCard;
  /**
   * 适用范围,0表示全场适用,1表示可用商品，2表示不可用商品
   */
  @ApiModelProperty(value = "适用范围,0表示全场适用,1表示可用商品，2表示不可用商品")
  private Integer scope;

  /**
   * 使用门槛，消费满xx元
   */
  @ApiModelProperty(value = "使用门槛，消费满xx元")
  private BigDecimal threshold;
  /**
   * 抵扣金额或折扣率
   */
  @ApiModelProperty(value = "抵扣金额或折扣率")
  private BigDecimal discount;
  /**
   * 创建者
   */
  @ApiModelProperty(value = "创建者")
  private Long creatorId;

  /**
   * 渠道
   */
  @ApiModelProperty(value = "渠道")
  private List<Integer> channelList;
}
