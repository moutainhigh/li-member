package com.siyueli.platform.member.response.payment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetRechargeResultResponse {

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "状态: 1-初始, 2-支付成功, 3-支付失败")
    private Integer status;
}
