package com.siyueli.platform.member.pojo.permissionlogin;

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
 * 访问的uri是否需要登录
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("permission_login")
public class PermissionLogin extends Model<PermissionLogin> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 访问的uri
     */
    private String uri;
    /**
     * 是否需要登录：0-不需要，1-需要
     */
    @TableField("need_login")
    private Integer needLogin;

    @TableField("uri_name")
    private String uriName;

    @TableField("uri_category")
    private String uriCategory;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;
    /**
     * 更新时间
     */
    @TableField("update_at")
    private Date updateAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
