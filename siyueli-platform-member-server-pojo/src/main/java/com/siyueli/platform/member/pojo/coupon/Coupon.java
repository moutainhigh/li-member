package com.siyueli.platform.member.pojo.coupon;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(value = "优惠券(Coupon)")
public class Coupon extends Model<Coupon> {

  private static final long serialVersionUID = -6350481865742946582L;

  /**
   * id
   */
  @TableId(value = "id", type = IdType.AUTO)
  @ApiModelProperty(value = "id")
  private Long id;

  /**
   * 卡券任务ID
   */
  @TableField("task_id")
  @ApiModelProperty(value = "卡券任务ID")
  private Long taskId;

  /**
   * 卡券类型
   */
  @TableField("task_type_id")
  @ApiModelProperty(value = "卡券类型")
  private Integer taskTypeId;

  /**
   * 优惠券
   */
  @ApiModelProperty(value = "优惠券code")
  private String code;

  @TableField("card_coupon_id")
  @ApiModelProperty(value = "微信卡券ID")
  private Long cardCouponId;

  /**
   * 会员ID
   */
  @TableField("member_id")
  @ApiModelProperty(value = "会员ID")
  private Long memberId;

  /**
   * 优惠券作用的订单号
   */
  @TableField("order_no")
  @ApiModelProperty(value = "优惠券作用的订单号")
  private String orderNo;

  /**
   * 领取的来源平台
   */
  @ApiModelProperty(value = "领取的来源平台")
  private Integer source;

  /**
   * 优惠券状态
   */
  @TableField("status_id")
  @ApiModelProperty(value = "优惠券状态")
  private Integer statusId;

  /**
   * 领取时间
   */
  @TableField("receipt_at")
  @ApiModelProperty(value = "领取时间")
  private LocalDateTime receiptAt;

  /**
   * 使用时间
   */
  @TableField("use_at")
  @ApiModelProperty(value = "使用时间")
  private LocalDateTime useAt;

  /**
   * 有效开始时间
   */
  @TableField("valid_time_start_at")
  @ApiModelProperty(value = "有效开始时间")
  private LocalDateTime validTimeStartAt;

  /**
   * 有效结束时间
   */
  @TableField("valid_time_end_at")
  @ApiModelProperty(value = "有效结束时间")
  private LocalDateTime validTimeEndAt;

  /**
   * 创建时间
   */
  @TableField("created_at")
  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createdAt;

  /**
   * 更新时间
   */
  @TableField("updated_at")
  @ApiModelProperty(value = "更新时间")
  private LocalDateTime updatedAt;

  public Coupon(String code) {
    this.code = code;
  }

  public Coupon(Long taskId) {
    this.taskId = taskId;
  }

  @Override protected Serializable pkVal() {
    return this.id;
  }
}
