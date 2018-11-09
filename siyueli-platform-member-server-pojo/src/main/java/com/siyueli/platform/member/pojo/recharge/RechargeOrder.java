package com.siyueli.platform.member.pojo.recharge;

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
import java.util.Date;

/**
 * <p>
 * 充值订单基础信息表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("recharge_order")
public class RechargeOrder extends Model<RechargeOrder> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主健id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "金额")
    private BigDecimal amount = new BigDecimal("0.0");


    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "订单号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建日期")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新日期")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
