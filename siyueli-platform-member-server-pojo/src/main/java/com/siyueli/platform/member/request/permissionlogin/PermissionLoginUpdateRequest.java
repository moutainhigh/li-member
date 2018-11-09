package com.siyueli.platform.member.request.permissionlogin;

import com.siyueli.platform.member.common.permissionlogin.PermissionLoginCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionLoginUpdateRequest extends PermissionLoginCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
