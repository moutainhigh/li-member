package com.siyueli.platform.member.common.rights;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 权益表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
public class MemberRightsVo {

  /**
   * 权益名称
   */
  @NotNull(message = "权益名称不能为空")
  @ApiModelProperty(value = "权益名称",required = true)
  private String name;
  /**
   * 权益类型
   */
  @NotNull(message = "权益类型不能为空")
  @ApiModelProperty(value = "权益类型:0:积分,1:包邮,2:停车",required = true)
  private Integer type;

  /**
   * 权益状态
   */
  @ApiModelProperty(value = "权益状态(默认有效):0:有效,1:无效")
  private Integer status = 0;

  /**
   * 权益说明
   */
  @ApiModelProperty(value = "权益说明")
  private String remarks;
  /**
   * 触发条件
   */
  @ApiModelProperty(value = "触发条件")
  @TableField("trigger_condition")
  private BigDecimal triggerCondition;
  /**
   * 权益内容
   */
  @ApiModelProperty(value = "权益内容")
  @TableField("rights_content")
  private BigDecimal rightsContent;


}
