package com.siyueli.platform.member.response.activity.getactivityinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetActivityInfoResponse {
    @ApiModelProperty(value = "活动信息")
    private ActivityInfoVo activityInfo;
}
