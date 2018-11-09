package com.siyueli.platform.member.response;

import com.siyueli.platform.member.common.PermissionRoleCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionRoleVo extends PermissionRoleCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    private Long id;

}
