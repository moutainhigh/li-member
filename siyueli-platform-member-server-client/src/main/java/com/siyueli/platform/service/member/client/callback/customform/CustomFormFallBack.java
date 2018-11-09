package com.siyueli.platform.service.member.client.callback.customform;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.*;
import com.siyueli.platform.member.response.customform.CustomFormVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.customform.CustomFormClient;
import org.springframework.stereotype.Component;

@Component
public class CustomFormFallBack extends BaseServiceFallBack implements CustomFormClient {
    @Override
    public ResponseData add(CustomFormAddRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(CustomFormUpdateRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<CustomFormVo> get(CustomFormGetRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<CustomFormVo>> search(CustomFormSearchRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(CustomFormDeleteRequest requestParam) {
        return getDownGradeResponse();
    }
}
