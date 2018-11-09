package com.siyueli.platform.member.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RoleCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("子系统")
    private Integer childrenSystem;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("可用范围")
    private Long scope;

    @ApiModelProperty("创建人")
    private Long creator;

    @ApiModelProperty("创建时间")
    private Date createdAt;

}
