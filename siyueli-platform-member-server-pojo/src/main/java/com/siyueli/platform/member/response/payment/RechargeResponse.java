package com.siyueli.platform.member.response.payment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RechargeResponse {
    private String orderNo;
    private String timeStamp;
    private String nonceStr;
    @ApiModelProperty(value = "要修改成package")
    private String pkg;
    private String signType;
    private String paySign;
}
