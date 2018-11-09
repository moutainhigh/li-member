package com.siyueli.platform.member.common.sportevent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SportEventSignUpFieldCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("赛事报名id")
    private Long signUpId;

    @ApiModelProperty("字段id")
    private Long fieldId;

    @ApiModelProperty("字段值")
    private String fieldValue;

}
