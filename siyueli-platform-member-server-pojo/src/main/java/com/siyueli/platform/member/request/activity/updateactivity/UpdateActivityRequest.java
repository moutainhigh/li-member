package com.siyueli.platform.member.request.activity.updateactivity;

import com.siyueli.platform.member.request.activity.common.CommonActivityRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateActivityRequest extends CommonActivityRequest {

    @ApiModelProperty(value = "活动id")
    private Long id;

    private List<UpdateActRoleInfoVo> actRoleInfoList;
}
