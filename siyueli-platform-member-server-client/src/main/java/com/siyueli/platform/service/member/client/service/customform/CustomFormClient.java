package com.siyueli.platform.service.member.client.service.customform;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.*;
import com.siyueli.platform.member.response.customform.CustomFormVo;
import com.siyueli.platform.service.member.client.callback.customform.CustomFormFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = CustomFormFallBack.class)
public interface CustomFormClient {

    @RequestMapping(value = "/customForm/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData add(@RequestBody CustomFormAddRequest requestParam);

    @RequestMapping(value = "/customForm/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData update(@RequestBody CustomFormUpdateRequest requestParam);

    @RequestMapping(value = "/customForm/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<CustomFormVo> get(@RequestBody CustomFormGetRequest requestParam);

    @RequestMapping(value = "/customForm/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PageResponse<CustomFormVo>> search(@RequestBody CustomFormSearchRequest requestParam);

    @RequestMapping(value = "/customForm/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData delete(@RequestBody CustomFormDeleteRequest requestParam);
}
