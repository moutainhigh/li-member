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
 * 表单
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("custom_form")
public class CustomForm extends Model<CustomForm> {

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
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;
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
