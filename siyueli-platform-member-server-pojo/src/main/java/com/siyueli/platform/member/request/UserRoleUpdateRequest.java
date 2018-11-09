package com.siyueli.platform.member.request;

import com.siyueli.platform.member.common.UserRoleCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRoleUpdateRequest extends UserRoleCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
