package com.siyueli.platform.service.member.client.callback.activity;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import com.siyueli.platform.member.request.activity.addactivity.AddActivityRequest;
import com.siyueli.platform.member.request.activity.updateactivity.UpdateActivityRequest;
import com.siyueli.platform.member.request.activity.updatestatus.UpdateStatusRequest;
import com.siyueli.platform.service.member.client.service.activity.ActivityService;
import org.springframework.stereotype.Component;

@Component
public class ActivityServiceFallBack implements ActivityService {


    @Override
    public ResponseData addActivity(AddActivityRequest addActivityRequest) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData updateActivity(UpdateActivityRequest updateActivityRequest) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData getActivityList(int page, int size) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData getCouponList(int page, int size) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData getUserList(int page, int size) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData getActivityInfo(Long id) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData updateStatus(UpdateStatusRequest updateStatusRequest) {
        return getDownGradeResponse();
    }

    private ResponseData getDownGradeResponse() {
        return ResponseData.build(
                ResponseBackCode.ERROR_DOWNGRADE.getValue(),
                ResponseBackCode.ERROR_DOWNGRADE.getMessage()
        );
    }
}
