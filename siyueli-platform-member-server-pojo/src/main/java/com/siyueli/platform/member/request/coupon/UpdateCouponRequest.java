package com.siyueli.platform.member.request.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateCouponRequest extends CommonCouponRequest {

    @ApiModelProperty(value = "id")
    private Long id;
}
