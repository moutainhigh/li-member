package com.siyueli.platform.member.pojo;

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
 * 权限表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("permission_action")
public class PermissionAction extends Model<PermissionAction> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限url
     */
    @TableField("action_url")
    private String actionUrl;
    /**
     * 显示名
     */
    @TableField("display_name")
    private String displayName;
    /**
     * 权限分组
     */
    @TableField("group_id")
    private Long groupId;
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
