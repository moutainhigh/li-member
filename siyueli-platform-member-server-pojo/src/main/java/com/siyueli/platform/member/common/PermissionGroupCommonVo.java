package com.siyueli.platform.member.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionGroupCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限组名")
    private String name;

    @ApiModelProperty("组类型:前台(0)或者后台(1)")
    private Integer type;

}
