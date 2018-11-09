package com.siyueli.platform.service.member.client.callback.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionActionVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.permission.PermissionActionClient;
import org.springframework.stereotype.Component;

@Component
public class PermissionActionFallBack extends BaseServiceFallBack implements PermissionActionClient {
    @Override
    public ResponseData add(PermissionActionAddRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(PermissionActionUpdateRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PermissionActionVo> get(PermissionActionGetRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<PermissionActionVo>> search(PermissionActionSearchRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(PermissionActionDeleteRequest requestParam) {
        return getDownGradeResponse();
    }
}
