package com.siyueli.platform.member.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    private Long userId;

    @ApiModelProperty("")
    private Long roleId;

}
