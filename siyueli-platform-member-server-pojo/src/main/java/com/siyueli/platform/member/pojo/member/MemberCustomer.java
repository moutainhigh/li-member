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
import java.util.Date;

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
@TableName("member_customer")
public class MemberCustomer extends Model<MemberCustomer> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 会员code
   */
  private String code;

  /**
   * 会员名称
   */
  private String name;

  /**
   * 会员等级
   */
  @TableField("grade_id")
  private String gradeId;

  /**
   * 手机号码
   */
  private String cellphone;

  /**
   * 座机号码
   */
  private String mobile;

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

  /**
   * 积分
   */
  private BigDecimal points;

  /**
   * 余额
   */
  private BigDecimal balance;

  /**
   * 押金
   */
  private BigDecimal deposite;

  /**
   * 卡券
   */
  @TableField("card_coup")
  private String cardCoup;

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
