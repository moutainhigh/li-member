package com.siyueli.platform.member.request;

import com.siyueli.platform.member.common.PermissionRoleCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionRoleUpdateRequest extends PermissionRoleCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
