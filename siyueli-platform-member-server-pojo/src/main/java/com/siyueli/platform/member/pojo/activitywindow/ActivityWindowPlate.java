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
 * 活动橱窗板块表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("activity_window_plate")
public class ActivityWindowPlate extends Model<ActivityWindowPlate> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 板块名
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 板块标记
     */
    @TableField("plate_flag")
    private String plateFlag;
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
