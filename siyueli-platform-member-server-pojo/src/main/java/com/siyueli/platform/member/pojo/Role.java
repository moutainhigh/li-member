package com.siyueli.platform.member.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 编号
     */
    private String code;
    /**
     * 子系统
     */
    @TableField("children_system")
    private Integer childrenSystem;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 可用范围
     */
    private Long scope;
    /**
     * 创建人
     */
    private Long creator;
    /**
     * 创建时间
     */
    @TableField("created_at")
    private Date createdAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
