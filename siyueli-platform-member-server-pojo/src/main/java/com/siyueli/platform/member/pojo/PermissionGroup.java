package com.siyueli.platform.member.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限分组
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("permission_group")
public class PermissionGroup extends Model<PermissionGroup> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限组名
     */
    private String name;
    /**
     * 组类型:前台(0)或者后台(1)
     */
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
