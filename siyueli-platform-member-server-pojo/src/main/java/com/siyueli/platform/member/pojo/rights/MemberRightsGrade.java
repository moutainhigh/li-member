package com.siyueli.platform.member.pojo.rights;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权益等级表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
@Accessors(chain = true)
@TableName("member_rights_grade")
public class MemberRightsGrade extends Model<MemberRightsGrade> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主健id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权益id
     */
    @NotNull(message = "权益id不能为空")
    @ApiModelProperty(value = "权益id")
    @TableField("rights_id")
    private Long rightsId;
    /**
     * 等级id
     */
    @NotNull(message = "等级id不能为空")
    @ApiModelProperty(value = "等级id")
    @TableField("grade_id")
    private Long gradeId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_at")
    private Date createAt = new Date();


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
