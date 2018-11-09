package com.siyueli.platform.service.member.client.callback.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionGroupVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.permission.PermissionGroupClient;
import org.springframework.stereotype.Component;

@Component
public class PermissionGroupFallBack extends BaseServiceFallBack implements PermissionGroupClient {
    @Override
    public ResponseData add(PermissionGroupAddRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(PermissionGroupUpdateRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PermissionGroupVo> get(PermissionGroupGetRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<PermissionGroupVo>> search(PermissionGroupSearchRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(PermissionGroupDeleteRequest requestParam) {
        return getDownGradeResponse();
    }
}
