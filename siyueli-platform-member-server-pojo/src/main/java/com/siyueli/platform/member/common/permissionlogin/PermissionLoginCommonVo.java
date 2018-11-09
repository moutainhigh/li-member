package com.siyueli.platform.member.common.permissionlogin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionLoginCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("访问的uri")
    private String uri;

    @ApiModelProperty("是否需要登录：0-不需要，1-需要")
    private Integer needLogin;

    @ApiModelProperty("接口名称")
    private String uriName;

    @ApiModelProperty("接口分类")
    private String uriCategory;

}
