package com.siyueli.platform.member.response.permissionlogin;

import com.siyueli.platform.member.common.permissionlogin.PermissionLoginCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PermissionLoginVo extends PermissionLoginCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("创建时间")
    private Date createAt;

    @ApiModelProperty("更新时间")
    private Date updateAt;

}
