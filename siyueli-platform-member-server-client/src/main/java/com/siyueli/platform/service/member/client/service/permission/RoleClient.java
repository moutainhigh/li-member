package com.siyueli.platform.service.member.client.service.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.RoleVo;
import com.siyueli.platform.service.member.client.callback.permission.RoleFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = RoleFallBack.class)
public interface RoleClient {

    @RequestMapping(value = "/role/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData add(@RequestBody RoleAddRequest requestParam);

    @RequestMapping(value = "/role/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData update(@RequestBody RoleUpdateRequest requestParam);

    @RequestMapping(value = "/role/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<RoleVo> get(@RequestBody RoleGetRequest requestParam);

    @RequestMapping(value = "/role/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PageResponse<RoleVo>> search(@RequestBody RoleSearchRequest requestParam);

    @RequestMapping(value = "/role/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData delete(@RequestBody RoleDeleteRequest requestParam);
}
