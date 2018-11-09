package com.siyueli.platform.member.common.sportevent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SportEventFormCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("表单id")
    private Long formId;

    @ApiModelProperty("分类id")
    private Long categoryId;

}
