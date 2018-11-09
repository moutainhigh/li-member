package com.siyueli.platform.service.member.client.callback.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionRoleVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.permission.PermissionRoleClient;
import org.springframework.stereotype.Component;

@Component
public class PermissionRoleFallBack extends BaseServiceFallBack implements PermissionRoleClient {
    @Override
    public ResponseData add(PermissionRoleAddRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(PermissionRoleUpdateRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PermissionRoleVo> get(PermissionRoleGetRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<PermissionRoleVo>> search(PermissionRoleSearchRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(PermissionRoleDeleteRequest requestParam) {
        return getDownGradeResponse();
    }
}
