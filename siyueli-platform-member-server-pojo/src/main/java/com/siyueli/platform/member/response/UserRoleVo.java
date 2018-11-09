package com.siyueli.platform.member.response;

import com.siyueli.platform.member.common.UserRoleCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleVo extends UserRoleCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    private Long id;

}
