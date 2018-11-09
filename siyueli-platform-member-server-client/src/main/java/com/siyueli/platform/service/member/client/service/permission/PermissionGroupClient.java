package com.siyueli.platform.service.member.client.service.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionGroupVo;
import com.siyueli.platform.service.member.client.callback.permission.PermissionGroupFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = PermissionGroupFallBack.class)
public interface PermissionGroupClient {

    @RequestMapping(value = "/permissionGroup/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData add(@RequestBody PermissionGroupAddRequest requestParam);

    @RequestMapping(value = "/permissionGroup/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData update(@RequestBody PermissionGroupUpdateRequest requestParam);

    @RequestMapping(value = "/permissionGroup/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PermissionGroupVo> get(@RequestBody PermissionGroupGetRequest requestParam);

    @RequestMapping(value = "/permissionGroup/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PageResponse<PermissionGroupVo>> search(@RequestBody PermissionGroupSearchRequest requestParam);

    @RequestMapping(value = "/permissionGroup/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData delete(@RequestBody PermissionGroupDeleteRequest requestParam);
}
