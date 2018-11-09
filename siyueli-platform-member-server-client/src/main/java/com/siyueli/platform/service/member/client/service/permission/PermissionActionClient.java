package com.siyueli.platform.service.member.client.service.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionActionVo;
import com.siyueli.platform.service.member.client.callback.permission.PermissionActionFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = PermissionActionFallBack.class)
public interface PermissionActionClient {

    @RequestMapping(value = "/permissionAction/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData add(@RequestBody PermissionActionAddRequest requestParam);

    @RequestMapping(value = "/permissionAction/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData update(@RequestBody PermissionActionUpdateRequest requestParam);

    @RequestMapping(value = "/permissionAction/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PermissionActionVo> get(@RequestBody PermissionActionGetRequest requestParam);

    @RequestMapping(value = "/permissionAction/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PageResponse<PermissionActionVo>> search(@RequestBody PermissionActionSearchRequest requestParam);

    @RequestMapping(value = "/permissionAction/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData delete(@RequestBody PermissionActionDeleteRequest requestParam);
}
