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
import java.time.LocalDateTime;

/**
 * <p>
 * 规则卡券用户表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("activity_user_coupon")
public class ActivityUserCoupon extends Model<ActivityUserCoupon> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主健id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 规则信息表id
     */
    @ApiModelProperty(value = "规则信息表id")
    @TableField("role_id")
    private Long roleId;
    /**
     * 卡券id
     */
    @ApiModelProperty(value = "卡券id")
    @TableField("coupon_id")
    private Long couponId;
    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id")
    @TableField("user_id")
    private Long userId;
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
