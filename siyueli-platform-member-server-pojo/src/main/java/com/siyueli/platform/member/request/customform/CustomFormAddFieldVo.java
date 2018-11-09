package com.siyueli.platform.member.request.customform;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomFormAddFieldVo {
    @ApiModelProperty("字段id")
    private Long fieldId;

    @ApiModelProperty("是否必填")
    private Integer required;

    @ApiModelProperty("排序")
    private Integer sort;
}
