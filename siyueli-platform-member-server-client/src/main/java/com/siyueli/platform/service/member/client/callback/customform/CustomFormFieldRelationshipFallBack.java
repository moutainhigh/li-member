package com.siyueli.platform.service.member.client.callback.customform;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.*;
import com.siyueli.platform.member.response.customform.CustomFormFieldRelationshipVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.customform.CustomFormFieldRelationshipClient;
import org.springframework.stereotype.Component;

@Component
public class CustomFormFieldRelationshipFallBack extends BaseServiceFallBack implements CustomFormFieldRelationshipClient {
    @Override
    public ResponseData add(CustomFormFieldRelationshipAddRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(CustomFormFieldRelationshipUpdateRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<CustomFormFieldRelationshipVo> get(CustomFormFieldRelationshipGetRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<CustomFormFieldRelationshipVo>> search(CustomFormFieldRelationshipSearchRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(CustomFormFieldRelationshipDeleteRequest requestParam) {
        return getDownGradeResponse();
    }
}
