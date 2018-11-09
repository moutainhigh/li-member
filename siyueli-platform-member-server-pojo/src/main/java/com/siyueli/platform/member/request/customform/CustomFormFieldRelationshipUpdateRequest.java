package com.siyueli.platform.member.request.customform;

import com.siyueli.platform.member.common.customform.CustomFormFieldRelationshipCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomFormFieldRelationshipUpdateRequest extends CustomFormFieldRelationshipCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
