package com.siyueli.platform.member.request.activitywindow.actwincontent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateActWinContentRequest extends ActWinContentCommonRequest {

    @ApiModelProperty("id")
    private Long id;
}
