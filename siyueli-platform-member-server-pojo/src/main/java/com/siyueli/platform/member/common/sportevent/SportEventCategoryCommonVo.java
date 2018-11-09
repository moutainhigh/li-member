package com.siyueli.platform.member.common.sportevent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SportEventCategoryCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父级id")
    private Long parentId;

}
