package com.siyueli.platform.member.request.activity.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CommonActivityRequest {


    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    @NotNull(message = "活动名称不能为空")
    private String name;
    /**
     * 活动类型
     */
    @ApiModelProperty(value = "活动类型-1:派发卡券，2-注册活动，3-充值活动，4-促销活动")
    @Range(min = 1, max = 4, message = "活动类型的值不合法")
    private Integer type;
    /**
     * 生效类型
     */
    @ApiModelProperty(value = "生效类型")
    @Range(min = 1, max = 2, message = "生效类型的值不合法")
    private Integer effectType;
    /**
     * 生效时间
     */
    @ApiModelProperty(value = "生效时间")
    private LocalDateTime effectTime;


}
