package com.siyueli.platform.service.member.client.service.activity;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.request.activity.addactivity.AddActivityRequest;
import com.siyueli.platform.member.request.activity.updateactivity.UpdateActivityRequest;
import com.siyueli.platform.member.request.activity.updatestatus.UpdateStatusRequest;
import com.siyueli.platform.service.member.client.callback.activity.ActivityServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "siyueli-member-service", fallback = ActivityServiceFallBack.class)
public interface ActivityService {



    @RequestMapping(value = "/activity/addActivity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData addActivity(@RequestBody AddActivityRequest addActivityRequest);

    @RequestMapping(value = "/activity/updateActivity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData updateActivity(@RequestBody UpdateActivityRequest updateActivityRequest);

    @RequestMapping(value = "/activity/getCouponList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData getCouponList(@RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping(value = "/activity/getUserList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData getUserList(@RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping(value = "/activity/getActivityList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData getActivityList(@RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping(value = "/activity/getActivityInfo", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData getActivityInfo(@RequestParam("id") Long id);

    @RequestMapping(value = "/activity/updateStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData updateStatus(@RequestBody UpdateStatusRequest updateStatusRequest);
}
