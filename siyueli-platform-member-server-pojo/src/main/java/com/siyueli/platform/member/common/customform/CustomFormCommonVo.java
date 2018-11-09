package com.siyueli.platform.member.common.customform;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomFormCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("分类id")
    private Long categoryId;

}
