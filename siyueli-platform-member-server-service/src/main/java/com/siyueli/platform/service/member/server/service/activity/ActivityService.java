package com.siyueli.platform.service.member.server.service.activity;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.request.activity.addactivity.AddActivityRequest;
import com.siyueli.platform.member.request.activity.getactivityinfo.GetActivityInfoRequest;
import com.siyueli.platform.member.request.activity.getactivitylist.GetActivityListRequest;
import com.siyueli.platform.member.request.activity.getcouponlist.GetCouponListRequest;
import com.siyueli.platform.member.request.activity.getuserlist.GetUserListRequest;
import com.siyueli.platform.member.request.activity.updateactivity.UpdateActivityRequest;

public interface ActivityService {

    ResponseData getCouponList(GetCouponListRequest getCouponListRequest);

    ResponseData getUserList(GetUserListRequest getUserListRequest);

    ResponseData getActivityList(GetActivityListRequest getActivityListRequest);

    ResponseData getActivityInfo(GetActivityInfoRequest getActivityInfoRequest);

    ResponseData addActivity(AddActivityRequest addActivityRequest);

    ResponseData updateActivity(UpdateActivityRequest updateActivityRequest);

    ResponseData updateStatus(Long id, Integer status);
}
