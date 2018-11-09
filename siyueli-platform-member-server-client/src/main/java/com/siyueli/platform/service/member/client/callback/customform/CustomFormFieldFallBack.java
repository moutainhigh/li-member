package com.siyueli.platform.service.member.client.callback.customform;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.customformfield.*;
import com.siyueli.platform.member.response.customform.CustomFormFieldVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.customform.CustomFormFieldClient;
import org.springframework.stereotype.Component;

@Component
public class CustomFormFieldFallBack extends BaseServiceFallBack implements CustomFormFieldClient {
    @Override
    public ResponseData add(AddCustomFormFieldRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(UpdateCustomFormFieldRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<CustomFormFieldVo> get(GetCustomFormFieldRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<CustomFormFieldVo>> search(SearchCustomFormFieldRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(DeleteCustomFormFieldRequest requestParam) {
        return getDownGradeResponse();
    }
}
