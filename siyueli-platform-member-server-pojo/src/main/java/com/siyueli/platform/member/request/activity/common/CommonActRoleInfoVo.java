package com.siyueli.platform.member.request.activity.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommonActRoleInfoVo {

    // 派发活动字段
    @ApiModelProperty(value = "（派发活动字段）-卡券ID")
    private Long couponId;
    @ApiModelProperty(value = "（派发活动字段）-派发用户类型")
    private Integer distributeUserType;

    //注册和充值活动字段
    /**
     * 赠送积分
     */
    @ApiModelProperty(value = "（注册和充值活动字段）-赠送积分")
    private BigDecimal points;

    // 充值活动字段
    @ApiModelProperty(value = "（充值活动字段）-充值金额")
    private BigDecimal rechargeAmout;
    @ApiModelProperty(value = "（充值活动字段）-赠送余额")
    private BigDecimal donateBalance;

    // 促销活动字段
    @ApiModelProperty(value = "（促销活动字段）-促销类型")
    private Integer promoteType;
}
