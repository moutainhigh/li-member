package com.siyueli.platform.service.member.server.service.redis;

import com.siyueli.platform.member.response.permissionlogin.PermissionLoginVo;

import java.util.List;

public interface PermissionLoginRedisService {

    public void saveList(List<PermissionLoginVo> list);

    public List<PermissionLoginVo> getList();

    public void delList();
}
