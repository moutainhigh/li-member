package com.siyueli.platform.member.pojo.paymentcode;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 付款码表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("payment_code")
public class PaymentCode extends Model<PaymentCode> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 付款码
     */
    @TableField("payment_code")
    private String paymentCode;
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 金额，单位为分
     */
    private BigDecimal amount;
    /**
     * 有效时间
     */
    @TableField("valid_time")
    private LocalDateTime validTime;
    /**
     * 是否使用：0-没有使用，1-已经使用
     */
    @TableField("is_used")
    private Integer isUsed;
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
