package com.siyueli.platform.service.member.client.service.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionRoleVo;
import com.siyueli.platform.service.member.client.callback.permission.PermissionRoleFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = PermissionRoleFallBack.class)
public interface PermissionRoleClient {

    @RequestMapping(value = "/permissionRole/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData add(@RequestBody PermissionRoleAddRequest requestParam);

    @RequestMapping(value = "/permissionRole/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData update(@RequestBody PermissionRoleUpdateRequest requestParam);

    @RequestMapping(value = "/permissionRole/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PermissionRoleVo> get(@RequestBody PermissionRoleGetRequest requestParam);

    @RequestMapping(value = "/permissionRole/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PageResponse<PermissionRoleVo>> search(@RequestBody PermissionRoleSearchRequest requestParam);

    @RequestMapping(value = "/permissionRole/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData delete(@RequestBody PermissionRoleDeleteRequest requestParam);
}
