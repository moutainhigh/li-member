package com.siyueli.platform.member.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限角色对应表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("permission_role")
public class PermissionRole extends Model<PermissionRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;
    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;
    /**
     * 权限组id
     */
    @TableField("permission_group_id")
    private Long permissionGroupId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
