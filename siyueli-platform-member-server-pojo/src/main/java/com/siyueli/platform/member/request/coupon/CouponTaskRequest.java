/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.coupon;

import com.siyueli.platform.member.constants.coupon.ChannelType;
import com.siyueli.platform.member.constants.coupon.CouponTaskType;
import com.siyueli.platform.member.constants.coupon.ScopeType;
import com.siyueli.platform.member.request.BaseRequest;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "卡券请求(CouponTaskRequest)")
public class CouponTaskRequest extends BaseRequest {

  /**
   * 卡券名称
   */
  @ApiModelProperty(value = "卡券名称")
  @NotBlank(message = "卡券名称不能为空")
  private String name;

  /**
   * 卡券类型
   */
  @ApiModelProperty(value = "卡券类型 0：代金券，1：打折券，2:赠品券")
  @NotNull(message = "卡券类型不能为空")
  private Integer typeId;

  /**
   * 领取开始时间
   */
  @ApiModelProperty(value = "领取开始时间")
  @NotNull(message = "领取开始时间不能为空")
  private LocalDateTime startAt;

  /**
   * 领取结束时间
   */
  @ApiModelProperty(value = "领取结束时间")
  @NotNull(message = "领取结束不能为空")
  @FutureOrPresent(message = "领取结束时间,必须是现在或将来时间")
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
  @FutureOrPresent(message = "有效结束时间,必须是现在或将来时间")
  private LocalDateTime validTimeEndAt;

  /**
   * 从第几天起有效，0表示当天有效
   */
  @ApiModelProperty(value = "从第几天起有效，0表示当天有效")
  @Range(max = 30, message = "第几天有效,最大30天")
  private Integer receiptStartDay;

  /**
   * 有效后结束天数
   */
  @ApiModelProperty(value = "有效后结束天数")
  @Range(max = 90, message = "有效后结束天数,最大90天")
  private Integer receiptEndDay;

  /**
   * 适用渠道,0表示全渠道适用
   */
  @ApiModelProperty(value = "适用渠道,0：全渠道适用，1：线上，2：线下")
  @NotNull(message = "适用渠道不能为空")
  private List<Integer> channels;

  /**
   * 限领张数
   */
  @ApiModelProperty(value = "限领张数")
  @NotNull(message = "限领张数不能为空")
  private Integer receiptLimit;

  /**
   * 库存数
   */
  @ApiModelProperty(value = "库存数")
  @NotNull(message = "库存数不能为空")
  private Integer stockQty;

  /**
   * 是否与其他优惠活动共享
   */
  @ApiModelProperty(value = "是否与其他优惠活动共享，0：不与其他优惠活动共享，1：可与其他优惠活动共享")
  @NotNull(message = "活动共享不能为空")
  private Integer shareActivity;

  /**
   * 是否与会员卡优惠共享
   */
  @ApiModelProperty(value = "是否与会员卡优惠共享，0：不与会员卡共享，1：与会员卡共享")
  @NotNull(message = "会员卡不能为空")
  private Integer shareClubCard;

  /**
   * 适用范围,0表示全场适用
   */
  @ApiModelProperty(value = "适用范围,0表示全场适用，1：指定范围：可用商品，2：指定范围：不可用商品")
  @Range(min = 0, max = 2, message = "适用范围在0-2")
  private Integer scope;

  /**
   * 使用门槛，消费满xx元
   */
  @ApiModelProperty(value = "使用门槛，消费满xx元")
  @NotNull(message = "使用门槛不能为空")
  private BigDecimal threshold;

  /**
   * 抵扣金额或折扣率
   */
  @ApiModelProperty(value = "抵扣金额或折扣率")
  private BigDecimal discount;

  /**
   * 适用范围SKU列表
   */
  @ApiModelProperty(value = "适用范围SKU列表")
  private List<String> scopeSkus;

  /**
   * 赠品列表
   */
  @ApiModelProperty(value = "赠品列表")
  private List<ScopeGift> scopeGifts;

  @Data
  @ApiModel(value = "赠品列表(ScopeGift)")
  public static class ScopeGift {

    /**
     * 赠品SKU
     */
    @ApiModelProperty(value = "赠品SKU")
    @NotNull(message = "赠品SKU不能为空")
    private String skuNo;

    /**
     * 赠品数量
     */
    @ApiModelProperty(value = "赠品数量")
    @NotNull(message = "赠品数量不能为空")
    private Integer qty;
  }

  @Override
  public BindingResult appendValidation(BindingResult errors) {

    String errorCode = String.valueOf(ResponseBackCode.ERROR_PARAM_INVALID.getValue());
    if (this.validTimeStartAt == null && this.validTimeEndAt == null) {
      if (this.receiptStartDay == null && this.receiptEndDay == null) {
        errors.reject(errorCode, "有效时间与有效天数必须填一种");
      }
    }

    if (channels.size() <= 0) {
      errors.reject(errorCode, "渠道不能为空");
    } else {
      Boolean isAllChannel = false;
      for (Integer channel : channels) {
        if (ChannelType.ALL.getValue().equals(channel)) {
          isAllChannel = true;
        }
        if (!ChannelType.contains(channel)) {
          errors.reject(errorCode, "渠道ID(" + channel + ")不存在");
        }
      }

      if (isAllChannel && channels.size() > 1) {
        errors.reject(errorCode, "渠道列表有误，又含全渠道又含其他渠道");
      }
    }

    if (!this.typeId.equals(CouponTaskType.GIFT.getValue())) {
      // 代金券与折扣券
      if (this.discount == null) {
        errors.reject(errorCode, "代金券与折扣券，折扣金额或折扣必填");
      }
      if (!this.scope.equals(ScopeType.ALL.getValue())) {
        if (this.scopeSkus == null || this.scopeSkus.size() <= 0) {
          errors.reject(errorCode, "代金券与折扣券，适用范围对应的商品为空");
        }
      }
    } else {
      // 赠品券
      if (this.scopeGifts == null || this.scopeGifts.size() <= 0) {
        errors.reject(errorCode, "赠品券，赠品不能为空");
      }
    }

    return errors;
  }
}
