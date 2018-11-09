package com.siyueli.platform.member.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionRoleCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("权限id")
    private Long permissionId;

    @ApiModelProperty("权限组id")
    private Long permissionGroupId;

}
