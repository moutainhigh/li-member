package com.siyueli.platform.member.common.accountchecking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountCheckingCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("金额")
    private BigDecimal money;

    @ApiModelProperty("备注")
    private String remark;

}
