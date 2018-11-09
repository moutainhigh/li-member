package com.siyueli.platform.service.member.server.controller.activity;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.request.activity.addactivity.AddActivityRequest;
import com.siyueli.platform.member.request.activity.getactivityinfo.GetActivityInfoRequest;
import com.siyueli.platform.member.request.activity.getactivitylist.GetActivityListRequest;
import com.siyueli.platform.member.request.activity.getcouponlist.GetCouponListRequest;
import com.siyueli.platform.member.request.activity.getuserlist.GetUserListRequest;
import com.siyueli.platform.member.request.activity.updateactivity.UpdateActivityRequest;
import com.siyueli.platform.member.request.activity.updatestatus.UpdateStatusRequest;
import com.siyueli.platform.service.member.server.annotation.LoggerAspectAnnotation;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.service.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 活动管理控制类
 * @author gavin
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {



    @Autowired
    private ActivityService activityService;


    /**
     * 新增活动
     * @param addActivityRequest
     * @return
     */
    @LogAnnotation
    @PostMapping("/addActivity")
    public ResponseData addActivity(@RequestBody AddActivityRequest addActivityRequest) {
        try {
            return activityService.addActivity(addActivityRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

    /**
     * 修改活动
     * @param updateActivityRequest
     * @return
     */
    @LogAnnotation
    @PostMapping("/updateActivity")
    public ResponseData updateActivity(@RequestBody UpdateActivityRequest updateActivityRequest) {
        try {
            return activityService.updateActivity(updateActivityRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }


    /**
     * 得到卡券列表
     * @return
     */
    @LogAnnotation
    @GetMapping("/getCouponList")
    public ResponseData getCouponList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
        try {
            GetCouponListRequest getCouponListRequest = new GetCouponListRequest();
            getCouponListRequest.setPage(page);
            getCouponListRequest.setSize(size);
            return activityService.getCouponList(getCouponListRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

    /**
     * 得到用户列表
     * @return
     */
    @LogAnnotation
    @GetMapping("/getUserList")
    public ResponseData getUserList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
        try {
            GetUserListRequest getUserListRequest = new GetUserListRequest();
            getUserListRequest.setPage(page);
            getUserListRequest.setSize(size);
            return activityService.getUserList(getUserListRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

    /**
     * 得到活动列表
     * @return
     */
    @LogAnnotation
    @GetMapping("/getActivityList")
    public ResponseData getActivityList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
        try {

            GetActivityListRequest getActivityListRequest = new GetActivityListRequest();
            getActivityListRequest.setPage(page);
            getActivityListRequest.setSize(size);
            return activityService.getActivityList(getActivityListRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

    @LogAnnotation
    //@LoggerAspectAnnotation
    @GetMapping("/getActivityInfo")
    public ResponseData getActivityInfo(@RequestParam("id") Long id) {
        try {
            GetActivityInfoRequest getActivityInfoRequest = new GetActivityInfoRequest();
            getActivityInfoRequest.setId(id);
            return activityService.getActivityInfo(getActivityInfoRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

    @LogAnnotation
    @PostMapping("/updateStatus")
    public ResponseData updateStatus(@RequestBody UpdateStatusRequest updateStatusRequest) {
        try {
            return activityService.updateStatus(updateStatusRequest.getId(), updateStatusRequest.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

}
