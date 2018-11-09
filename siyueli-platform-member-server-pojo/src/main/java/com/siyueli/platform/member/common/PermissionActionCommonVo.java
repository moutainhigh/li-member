package com.siyueli.platform.member.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PermissionActionCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限url")
    private String actionUrl;

    @ApiModelProperty("显示名")
    private String displayName;

    @ApiModelProperty("权限分组")
    private Long groupId;

    @ApiModelProperty("创建时间")
    private Date createdAt;

}
