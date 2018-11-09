package com.siyueli.platform.member.pojo.activitywindow;

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
 * 活动橱窗分组表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("activity_window_group")
public class ActivityWindowGroup extends Model<ActivityWindowGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 分组名
     */
    private String name;
    /**
     * 所属板块
     */
    @TableField("plate_id")
    private Long plateId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 分组标记
     */
    @TableField("group_flag")
    private String groupFlag;
    /**
     * 状态
     */
    private Integer status;
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
