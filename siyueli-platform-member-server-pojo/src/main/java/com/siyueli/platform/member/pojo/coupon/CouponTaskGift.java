package com.siyueli.platform.member.pojo.coupon;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 卡券任务赠品
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon_task_gift")
@NoArgsConstructor
public class CouponTaskGift extends Model<CouponTaskGift> {

    private static final long serialVersionUID = -1047807942681772435L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 卡券任务ID
     */
    @TableField("task_id")
    private Long taskId;
    /**
     * 赠品SKU-NO编码
     */
    @TableField("sku_no")
    private String skuNo;
    /**
     * 赠品数量
     */
    private Integer qty;
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

    public CouponTaskGift(Long taskId) {
        this.taskId = taskId;
    }

    @Override protected Serializable pkVal() {
        return this.id;
    }


}
