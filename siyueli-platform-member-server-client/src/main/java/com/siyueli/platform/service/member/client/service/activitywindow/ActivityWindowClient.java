package com.siyueli.platform.service.member.client.service.activitywindow;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.request.activitywindow.actwincontent.*;
import com.siyueli.platform.member.request.activitywindow.actwingroup.*;
import com.siyueli.platform.member.request.activitywindow.actwinplate.*;
import com.siyueli.platform.service.member.client.callback.activitywindow.ActivityWindowFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = ActivityWindowFallBack.class)
public interface ActivityWindowClient {

    @RequestMapping(value = "/activityWindow/addPlate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData addPlate(@RequestBody AddActWinPlateRequest requestParam);

    @RequestMapping(value = "/activityWindow/updatePlate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData updatePlate(@RequestBody UpdateActWinPlateRequest requestParam);

    @RequestMapping(value = "/activityWindow/getPlate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData getPlate(@RequestBody GetActWinPlateRequest requestParam);

    @RequestMapping(value = "/activityWindow/searchPlate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData searchPlate(@RequestBody SearchActWinPlateRequest requestParam);

    @RequestMapping(value = "/activityWindow/deletePlate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData deletePlate(@RequestBody DeleteActWinPlateRequest requestParam);

    @RequestMapping(value = "/activityWindow/addGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData addGroup(@RequestBody AddActWinGroupRequest requestParam);

    @RequestMapping(value = "/activityWindow/updateGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData updateGroup(@RequestBody UpdateActWinGroupRequest requestParam);

    @RequestMapping(value = "/activityWindow/getGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData getGroup(@RequestBody GetActWinGroupRequest requestParam);

    @RequestMapping(value = "/activityWindow/searchGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData searchGroup(@RequestBody SearchActWinGroupRequest requestParam);

    @RequestMapping(value = "/activityWindow/deleteGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData deleteGroup(@RequestBody DeleteActWinGroupRequest requestParam);

    @RequestMapping(value = "/activityWindow/addContent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData addContent(@RequestBody AddActWinContentRequest requestParam);

    @RequestMapping(value = "/activityWindow/updateContent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData updateContent(@RequestBody UpdateActWinContentRequest requestParam);

    @RequestMapping(value = "/activityWindow/getContent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData getContent(@RequestBody GetActWinContentRequest requestParam);

    @RequestMapping(value = "/activityWindow/searchContent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData searchContent(@RequestBody SearchActWinContentRequest requestParam);

    @RequestMapping(value = "/activityWindow/deleteContent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData deleteContent(@RequestBody DeleteActWinContentRequest requestParam);
}
