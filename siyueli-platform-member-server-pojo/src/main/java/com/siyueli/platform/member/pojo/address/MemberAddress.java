package com.siyueli.platform.member.pojo.address;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 会员地址表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
@TableName("member_address")
public class MemberAddress extends Model<MemberAddress> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 用户id
   */
  @TableField("user_id")
  private Long userId;

  /**
   * 收货人
   */
  @TableField("receiver_name")
  private String receiverName;

  /**
   * 手机号码
   */
  private String cellphone;

  /**
   * 详细地址
   */
  private String address;

  /**
   * 收货人
   */
  private String fullAddress;

  /**
   * 省code
   */
  private Long provinceCode;

  /**
   * 市code
   */
  @TableField("city_code")
  private Long cityCode;

  /**
   * 区code
   */
  @TableField("district_code")
  private Long districtCode;


  /**
   * 标签
   */
  private String label;

  /**
   * 默认地址:0代表不是默认地址，1代表默认地址
   */
  @TableField("default_address")
  private Integer defaultAddress;

  /**
   * 状态:0代表有效，1代表失效(删除)
   */
  private Integer status = 0;

  /**
   * 创建时间
   */
  @TableField("create_at")
  private LocalDateTime createAt;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
