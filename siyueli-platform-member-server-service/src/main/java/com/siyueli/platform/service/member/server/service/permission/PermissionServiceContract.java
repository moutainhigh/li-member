package com.siyueli.platform.service.member.server.service.permission;

import com.siyueli.platform.member.response.PermissionActionVo;

import java.util.List;

public interface PermissionServiceContract {

    public List<PermissionActionVo> getPermissionListByUserId(Long userId);
}
