package com.siyueli.platform.member.request;

import com.siyueli.platform.member.common.PermissionGroupCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionGroupUpdateRequest extends PermissionGroupCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
