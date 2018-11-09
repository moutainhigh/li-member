package com.siyueli.platform.member.request.customform;

import com.siyueli.platform.member.common.customform.CustomFormCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomFormUpdateRequest extends CustomFormCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
