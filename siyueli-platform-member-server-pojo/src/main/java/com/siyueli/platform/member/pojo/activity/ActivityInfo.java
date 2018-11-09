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
 * 活动基础信息表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("activity_info")
public class ActivityInfo extends Model<ActivityInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主健id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    private String name;
    /**
     * 活动类型
     */
    @ApiModelProperty(value = "活动类型")
    private Integer type;
    /**
     * 生效类型
     */
    @ApiModelProperty(value = "生效类型")
    @TableField("effect_type")
    private Integer effectType;
    /**
     * 生效时间
     */
    @ApiModelProperty(value = "生效时间")
    @TableField("effect_time")
    private LocalDateTime effectTime;

    @ApiModelProperty(value = "状态：1-启用，0-禁用")
    private Integer status;

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
