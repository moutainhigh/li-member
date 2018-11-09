package com.siyueli.platform.member.pojo.member;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 会员客户表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
@TableName("member_user")
public class MemberUser extends Model<MemberUser> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 会员号
   */
  @TableField("code")
  private String code;

  /**
   * 会员名称
   */
  private String name;

  /**
   * 会员等级
   */
  @TableField("grade_id")
  private Long gradeId;

  /**
   * 等级名称
   */
  @TableField(exist = false)
  private String gradeName;

  /**
   * 手机号码
   */
  private String cellphone;

  /**
   * 注册渠道
   */
  @ApiModelProperty(value = "注册渠道")
  private String registerChannel;
  /**
   * 微信unnitId
   */
  @TableField("unnit_id")
  private String openId;
  /**
   * 车牌号
   */
  @TableField("car_num")
  private String carNum;
  /**
   * 生日
   */
  private LocalDateTime birthday;

  /**
   * 身份证号码
   */
  @TableField("identity_id")
  private String identityId;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 密码
   */
  private String password;

  /**
   * 状态
   */
  private Integer status;

  @TableField(exist = false)
  private String verificateCode;


  /**
   * 积分
   */
  private BigDecimal points = new BigDecimal("0.0");

  /**
   * 余额
   */
  private BigDecimal balance = new BigDecimal("0.0");

  /**
   * 赠送余额
   */
  @TableField("donate_balance")
  private BigDecimal donateBalance= new BigDecimal("0.0");

  /**
   * 压金
   */
  private BigDecimal deposite = new BigDecimal("0.0");

  /**
   * 注册时间
   */
  @TableField("create_at")
  private LocalDateTime createAt;

  /**
   * 更新时间
   */
  @TableField("update_at")
  private LocalDateTime updateAt;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
