package com.siyueli.platform.service.member.client.controller.activity;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.activity.addactivity.AddActivityRequest;
import com.siyueli.platform.member.request.activity.getactivityinfo.GetActivityInfoRequest;
import com.siyueli.platform.member.request.activity.getactivitylist.GetActivityListRequest;
import com.siyueli.platform.member.request.activity.getcouponlist.GetCouponListRequest;
import com.siyueli.platform.member.request.activity.getuserlist.GetUserListRequest;
import com.siyueli.platform.member.request.activity.updateactivity.UpdateActivityRequest;
import com.siyueli.platform.member.request.activity.updatestatus.UpdateStatusRequest;
import com.siyueli.platform.member.response.activity.getcouponlist.ActCouponTaskResponse;
import com.siyueli.platform.member.response.activity.getuserlist.ActMemberUserResponse;
import com.siyueli.platform.member.response.activity.getactivityinfo.ActivityInfoVo;
import com.siyueli.platform.member.response.activity.getactivityinfo.GetActivityInfoResponse;
import com.siyueli.platform.service.member.client.service.activity.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "斯越里_后台_活动管理接口")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    /**
     * 新增活动
     * @param addActivityRequest
     * @param result
     * @return
     */
    @LogAnnotation
    @ApiOperation(nickname = "addActivity",value = "新增活动")
    @PostMapping("/addActivity")
    public ResponseData addActivity(@Valid @RequestBody AddActivityRequest addActivityRequest, BindingResult result) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return ResponseData.build(
                    ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
                    ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
            );
        }
        ResponseData responseData = activityService.addActivity(addActivityRequest);
        return responseData;
    }

    /**
     * 修改活动
     * @param updateActivityRequest
     * @param result
     * @return
     */
    @LogAnnotation
    @ApiOperation(nickname = "updateActivity",value = "修改活动")
    @PutMapping("/updateActivity")
    public ResponseData updateActivity(@Valid @RequestBody UpdateActivityRequest updateActivityRequest, BindingResult result) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return ResponseData.build(
                    ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
                    ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
            );
        }
        ResponseData responseData = activityService.updateActivity(updateActivityRequest);
        return responseData;
    }

    /**
     * 得到卡券列表
     * @param getCouponListRequest
     * @param result
     * @return
     */
    @LogAnnotation
    @ApiOperation(nickname = "getCouponList",value = "得到卡券列表")
    @GetMapping("/getCouponList")
    public ResponseData<PageResponse<ActCouponTaskResponse>> getCouponList(@Valid GetCouponListRequest getCouponListRequest, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return ResponseData.build(
                    ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
                    ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
            );
        }
        ResponseData responseData = activityService.getCouponList(getCouponListRequest.getPage(), getCouponListRequest.getSize());
        return responseData;
    }

    /**
     * 得到用户列表
     * @param getUserListRequest
     * @param result
     * @return
     */
    @LogAnnotation
    @ApiOperation(nickname = "getUserList",value = "得到用户列表")
    @GetMapping("/getUserList")
    public ResponseData<PageResponse<ActMemberUserResponse>> getUserList(@Valid GetUserListRequest getUserListRequest, BindingResult result) {
        ResponseData errorResponse = getErrorResponse(result);
        if (errorResponse != null)
            return errorResponse;

        ResponseData responseData = activityService.getUserList(getUserListRequest.getPage(), getUserListRequest.getSize());
        return responseData;
    }

    private ResponseData getErrorResponse(BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return ResponseData.build(
                    ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
                    ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
            );
        }
        return null;
    }

    /**
     * 得到活动列表
     * @param getActivityListRequest
     * @param result
     * @return
     */
    @LogAnnotation
    @ApiOperation(nickname = "getActivityList",value = "得到活动列表")
    @GetMapping("/getActivityList")
    public ResponseData<PageResponse<ActivityInfoVo>> getActivityList(@Valid GetActivityListRequest getActivityListRequest, BindingResult result) {
        ResponseData errorResponse = getErrorResponse(result);
        if (errorResponse != null)
            return errorResponse;

        ResponseData responseData = activityService.getActivityList(getActivityListRequest.getPage(), getActivityListRequest.getSize());
        return responseData;
    }

    /**
     *
     * @return
     */
    @LogAnnotation
    @ApiOperation(nickname = "getActivityInfo",value = "得到活动信息")
    @GetMapping("/getActivityInfo/{id}")
    public ResponseData<GetActivityInfoResponse> getActivityInfo(@PathVariable("id") Long id) {
        ResponseData responseData = activityService.getActivityInfo(id);
        return responseData;
    }

    @LogAnnotation
    @ApiOperation(nickname = "updateStatus",value = "更新活动状态")
    @PutMapping("/updateStatus")
    public ResponseData updateStatus(@RequestBody UpdateStatusRequest updateStatusRequest) {
        ResponseData responseData = activityService.updateStatus(updateStatusRequest);
        return responseData;
    }
}
