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
 * 活动橱窗内容表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("activity_window_content")
public class ActivityWindowContent extends Model<ActivityWindowContent> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 内容名
     */
    private String name;
    /**
     * 分组id
     */
    @TableField("group_id")
    private Long groupId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图片url
     */
    @TableField("pic_url")
    private String picUrl;
    /**
     * 内容url
     */
    @TableField("content_url")
    private String contentUrl;
    /**
     * 描述
     */
    private String description;
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
