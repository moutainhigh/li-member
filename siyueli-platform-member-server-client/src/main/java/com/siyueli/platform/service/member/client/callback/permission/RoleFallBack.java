package com.siyueli.platform.service.member.client.callback.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.RoleVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.permission.RoleClient;
import org.springframework.stereotype.Component;

@Component
public class RoleFallBack extends BaseServiceFallBack implements RoleClient {
    @Override
    public ResponseData add(RoleAddRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(RoleUpdateRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<RoleVo> get(RoleGetRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<RoleVo>> search(RoleSearchRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(RoleDeleteRequest requestParam) {
        return getDownGradeResponse();
    }
}
