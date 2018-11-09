package com.siyueli.platform.member.request.payment;

import com.siyueli.platform.member.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetAccountLogRequest extends PageRequest {

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型：积分-0，余额-1")
    private Integer type;
}
