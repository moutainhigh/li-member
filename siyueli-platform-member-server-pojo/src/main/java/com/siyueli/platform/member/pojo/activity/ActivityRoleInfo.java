package com.siyueli.platform.member.pojo.activity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 规则信息表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("activity_role_info")
public class ActivityRoleInfo extends Model<ActivityRoleInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主健id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty(value = "卡券id")
    @TableField("coupon_id")
    private Long couponId;

    /**
     * 派发用户类型
     */
    @ApiModelProperty(value = "派发用户类型")
    @TableField("distribute_user_type")
    private Integer distributeUserType;
    /**
     * 赠送积分
     */
    @ApiModelProperty(value = "赠送积分")
    private BigDecimal points;
    /**
     * 充值金额
     */
    @ApiModelProperty(value = "充值金额")
    @TableField("recharge_amout")
    private BigDecimal rechargeAmout;
    /**
     * 赠送余额
     */
    @ApiModelProperty(value = "赠送余额")
    @TableField("donate_balance")
    private BigDecimal donateBalance;
    /**
     * 促销类型
     */
    @ApiModelProperty(value = "促销类型")
    @TableField("promote_type")
    private Integer promoteType;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_at")
    private LocalDateTime createAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
