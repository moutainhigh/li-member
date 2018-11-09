package com.siyueli.platform.member.pojo.customform;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字段选项表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("custom_field_option")
public class CustomFieldOption extends Model<CustomFieldOption> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 字段id
     */
    @TableField("field_id")
    private Long fieldId;
    /**
     * 字段选项值
     */
    @TableField("field_value")
    private String fieldValue;
    /**
     * 展示类型
     */
    @TableField("show_type")
    private Integer showType;
    /**
     * 更新时间
     */
    @TableField("update_at")
    private Date updateAt;
    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
