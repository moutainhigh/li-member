package com.siyueli.platform.service.member.client.callback.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.UserRoleVo;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.permission.UserRoleClient;
import org.springframework.stereotype.Component;

@Component
public class UserRoleFallBack extends BaseServiceFallBack implements UserRoleClient {
    @Override
    public ResponseData add(UserRoleAddRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData update(UserRoleUpdateRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<UserRoleVo> get(UserRoleGetRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData<PageResponse<UserRoleVo>> search(UserRoleSearchRequest requestParam) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData delete(UserRoleDeleteRequest requestParam) {
        return getDownGradeResponse();
    }
}
