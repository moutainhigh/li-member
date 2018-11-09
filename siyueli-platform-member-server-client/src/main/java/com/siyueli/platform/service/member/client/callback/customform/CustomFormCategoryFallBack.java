package com.siyueli.platform.service.member.client.callback.customform;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.customformcategory.*;
import com.siyueli.platform.member.response.customform.CustomFormCategoryVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.customform.CustomFormCategoryClient;
import org.springframework.stereotype.Component;

@Component
public class CustomFormCategoryFallBack extends BaseServiceFallBack implements CustomFormCategoryClient {
    @Override
    public ResponseData add(AddCustomFormCategoryRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(UpdateCustomFormCategoryRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<CustomFormCategoryVo> get(GetCustomFormCategoryRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<CustomFormCategoryVo>> search(SearchCustomFormCategoryRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(DeleteCustomFormCategoryRequest requestParam) {
        return getDownGradeResponse();
    }
}
