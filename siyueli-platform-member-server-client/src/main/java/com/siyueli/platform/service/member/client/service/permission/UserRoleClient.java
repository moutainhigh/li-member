package com.siyueli.platform.service.member.client.service.permission;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.UserRoleVo;
import com.siyueli.platform.service.member.client.callback.permission.UserRoleFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = UserRoleFallBack.class)
public interface UserRoleClient {

    @RequestMapping(value = "/userRole/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData add(@RequestBody UserRoleAddRequest requestParam);

    @RequestMapping(value = "/userRole/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData update(@RequestBody UserRoleUpdateRequest requestParam);

    @RequestMapping(value = "/userRole/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<UserRoleVo> get(@RequestBody UserRoleGetRequest requestParam);

    @RequestMapping(value = "/userRole/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PageResponse<UserRoleVo>> search(@RequestBody UserRoleSearchRequest requestParam);

    @RequestMapping(value = "/userRole/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData delete(@RequestBody UserRoleDeleteRequest requestParam);
}
