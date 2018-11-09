package com.siyueli.platform.member.request.activitywindow.actwingroup;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateActWinGroupRequest extends ActWinGroupCommonRequest {

    @ApiModelProperty("id")
    private Long id;
}
