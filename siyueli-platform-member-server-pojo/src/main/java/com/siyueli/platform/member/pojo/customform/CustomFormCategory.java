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
 * 表单分类表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("custom_form_category")
public class CustomFormCategory extends Model<CustomFormCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级id
     */
    @TableField("parent_id")
    private Long parentId;
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
