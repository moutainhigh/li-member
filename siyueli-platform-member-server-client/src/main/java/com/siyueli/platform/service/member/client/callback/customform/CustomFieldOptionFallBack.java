package com.siyueli.platform.service.member.client.callback.customform;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.customfieldoption.*;
import com.siyueli.platform.member.response.customform.CustomFieldOptionVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.customform.CustomFieldOptionClient;
import org.springframework.stereotype.Component;

@Component
public class CustomFieldOptionFallBack extends BaseServiceFallBack implements CustomFieldOptionClient {
    @Override
    public ResponseData add(AddCustomFieldOptionRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(UpdateCustomFieldOptionRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<CustomFieldOptionVo> get(GetCustomFieldOptionRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<CustomFieldOptionVo>> search(SearchCustomFieldOptionRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(DeleteCustomFieldOptionRequest requestParam) {
        return getDownGradeResponse();
    }
}
