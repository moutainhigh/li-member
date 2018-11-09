package com.siyueli.platform.member.response.activity.getactivityinfo;

import com.siyueli.platform.member.pojo.activity.ActivityRoleInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ActivityRoleInfoVo extends ActivityRoleInfo {

    @ApiModelProperty(value = "用户或卡券列表")
    private List<ActivityUserCouponVo> activityUserCouponList;
}
