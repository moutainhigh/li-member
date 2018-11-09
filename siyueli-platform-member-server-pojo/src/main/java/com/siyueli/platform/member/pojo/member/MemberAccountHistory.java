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
 * 帐户历史表
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
@TableName("member_account_history")
public class MemberAccountHistory extends Model<MemberAccountHistory> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 用户id
   */
  @TableField("user_id")
  private Long userId;

  /**
   * 积分
   */
  private BigDecimal points = new BigDecimal("0.0");

  /**
   * 期末积分
   */
  @TableField("terminal_points")
  private BigDecimal terminalPoints = new BigDecimal("0.0");

  /**
   * 余额
   */
  private BigDecimal balance = new BigDecimal("0.0");

  /**
   * 期末余额
   */
  @TableField("terminal_balance")
  private BigDecimal terminalBalance = new BigDecimal("0.0");

  /**
   * 压金
   */
  private BigDecimal deposite = new BigDecimal("0.0");

  /**
   * 类型
   */
  private Integer type;

  /**
   * 每种类型中的小类型
   */
  @TableField("small_type")
  private Integer smallType;




  /**
   * 创建时间
   */
  @TableField("create_at")
  private Date createAt = new Date();

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
