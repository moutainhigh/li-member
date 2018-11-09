package com.siyueli.platform.member.pojo.grade;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 会员等级表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
@TableName("member_grade")
public class MemberGrade extends Model<MemberGrade> implements Cloneable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 等级名称
     */
    private String name;
    /**
     * 背影图地址
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 背影图地址
     */
    @TableField("img_secret")
    private String imgSecret;
    /**
     * 晋升门槛
     */
    @TableField("promote_condition")
    private BigDecimal promoteCondition;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt = new Date();


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public Object clone() {
        MemberGradeCompose memberGradeCompose = new MemberGradeCompose();
        try{
            MemberGrade memberGrade = (MemberGrade)super.clone();
            memberGradeCompose.setId(memberGrade.getId());
            memberGradeCompose.setName(memberGrade.getName());
            memberGradeCompose.setImgUrl(memberGrade.getImgUrl());
            memberGradeCompose.setPromoteCondition(memberGrade.getPromoteCondition());
            memberGradeCompose.setCreateAt(memberGrade.getCreateAt());
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return memberGradeCompose;
    }

}
