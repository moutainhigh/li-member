package com.siyueli.platform.member.response.activity.getactivityinfo;

import com.siyueli.platform.member.pojo.activity.ActivityInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ActivityInfoVo extends ActivityInfo {

    @ApiModelProperty(value = "活动扩展信息")
    private List<ActivityRoleInfoVo> ariList;

}
