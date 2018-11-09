package com.siyueli.platform.member.pojo.coupon;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 卡券渠道关联表
 * </p>
 */
@Data
@Accessors(chain = true)
@TableName("coupon_task_channel")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CouponTaskChannel extends Model<CouponTaskChannel> {

  private static final long serialVersionUID = 1L;

  /**
   * 卡券ID
   */
  @TableField("coupon_task_id")
  private Long couponTaskId;

  /**
   * 渠道ID
   */
  @TableField("channel_id")
  private Integer channelId;

  /**
   * 创建时间
   */
  @TableField("created_at")
  private LocalDateTime createdAt;

  /**
   * 更新时间
   */
  @TableField("updated_at")
  private LocalDateTime updatedAt;

  public CouponTaskChannel(Long couponTaskId) {
    this.couponTaskId = couponTaskId;
  }

  @Override protected Serializable pkVal() {
    return (this.couponTaskId.toString() + this.channelId.toString());
  }
}
