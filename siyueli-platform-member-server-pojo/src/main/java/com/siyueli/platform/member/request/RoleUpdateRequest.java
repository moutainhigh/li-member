package com.siyueli.platform.member.request;

import com.siyueli.platform.member.common.RoleCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleUpdateRequest extends RoleCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
