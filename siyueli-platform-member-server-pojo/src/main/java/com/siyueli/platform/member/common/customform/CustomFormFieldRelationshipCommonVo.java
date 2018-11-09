package com.siyueli.platform.member.common.customform;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomFormFieldRelationshipCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("表单id")
    private Long formId;

    @ApiModelProperty("字段id")
    private Long fieldId;

    @ApiModelProperty("是否必填")
    private Integer required;

    @ApiModelProperty("排序")
    private Integer sort;

}
