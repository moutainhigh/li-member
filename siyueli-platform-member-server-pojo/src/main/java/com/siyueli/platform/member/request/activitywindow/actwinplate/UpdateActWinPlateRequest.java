package com.siyueli.platform.member.request.activitywindow.actwinplate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateActWinPlateRequest extends ActWinPlateCommonRequest {

    @ApiModelProperty("id")
    private Long id;
}
