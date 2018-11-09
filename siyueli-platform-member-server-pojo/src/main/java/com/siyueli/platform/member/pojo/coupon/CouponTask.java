package com.siyueli.platform.member.pojo.coupon;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 卡券任务
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon_task")
public class CouponTask extends Model<CouponTask> {

    private static final long serialVersionUID = -7969772764575111996L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 卡券名称
     */
    private String name;
    /**
     * 卡券类型
     */
    @TableField("type_id")
    private Integer typeId;
    /**
     * 卡券状态
     */
    @TableField("status_id")
    private Integer statusId;
    /**
     * 领取开始时间
     */
    @TableField("start_at")
    private LocalDateTime startAt;
    /**
     * 领取结束时间
     */
    @TableField("end_at")
    private LocalDateTime endAt;
    /**
     * 有效开始时间
     */
    @TableField("valid_time_start_at")
    private LocalDateTime validTimeStartAt;
    /**
     * 有效结束时间
     */
    @TableField("valid_time_end_at")
    private LocalDateTime validTimeEndAt;
    /**
     * 从第几天起有效，0表示当天有效
     */
    @TableField("receipt_start_day")
    private Integer receiptStartDay;
    /**
     * 有效后结束天数
     */
    @TableField("receipt_end_day")
    private Integer receiptEndDay;
    /**
     * 适用渠道列表,0表示全渠道适用
     */
    @TableField(exist = false)
    private List<Integer> channelList = new ArrayList<>();

    /**
     * 限领张数
     */
    @TableField("receipt_limit")
    private Integer receiptLimit;
    /**
     * 库存数
     */
    @TableField("stock_qty")
    private Integer stockQty;
    /**
     * 是否与其他优惠活动共享
     */
    @TableField("share_activity")
    private Integer shareActivity;
    /**
     * 是否与会员卡优惠共享
     */
    @TableField("share_club_card")
    private Integer shareClubCard;
    /**
     * 适用范围,0表示全场适用,1表示可用商品，2表示不可用商品
     */
    private Integer scope;

    /**
     * 使用门槛，消费满xx元
     */
    private BigDecimal threshold;
    /**
     * 抵扣金额或折扣率
     */
    private BigDecimal discount;
    /**
     * 创建者
     */
    @TableField("creator_id")
    private Long creatorId;
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
    /**
     * 软删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @Override protected Serializable pkVal() {
        return this.id;
    }

}
