package com.siyueli.platform.member.request;

import com.siyueli.platform.member.common.PermissionActionCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionActionUpdateRequest extends PermissionActionCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
