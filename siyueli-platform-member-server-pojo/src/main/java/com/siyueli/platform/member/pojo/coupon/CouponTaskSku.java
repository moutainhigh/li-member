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
 * 卡券任务适用商品/卡券任务不适用商品
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@TableName("coupon_task_sku")
@Accessors(chain = true)
public class CouponTaskSku extends Model<CouponTaskSku> {

    private static final long serialVersionUID = 2860388134205570665L;

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
     * SKU-NO编码
     */
    @TableField("sku_no")
    private String skuNo;
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

    public CouponTaskSku(Long taskId) {
        this.taskId = taskId;
    }

    @Override protected Serializable pkVal() {
        return this.id;
    }


}
