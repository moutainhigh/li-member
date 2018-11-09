package com.siyueli.platform.member.request.customform;

import com.siyueli.platform.member.common.customform.CustomFormCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CustomFormAddRequest extends CustomFormCommonVo {


    @ApiModelProperty("字段列表")
    private List<CustomFormAddFieldVo> fields;


}
