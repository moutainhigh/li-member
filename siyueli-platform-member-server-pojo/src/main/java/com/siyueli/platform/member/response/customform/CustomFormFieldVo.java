package com.siyueli.platform.member.response.customform;

import com.siyueli.platform.member.common.customform.CustomFormFieldCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CustomFormFieldVo extends CustomFormFieldCommonVo {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("更新时间")
    private Date updateAt;

    @ApiModelProperty("创建时间")
    private Date createAt;
}
