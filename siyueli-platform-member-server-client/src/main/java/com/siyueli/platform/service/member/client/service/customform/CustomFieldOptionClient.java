package com.siyueli.platform.service.member.client.service.customform;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.customfieldoption.*;
import com.siyueli.platform.member.response.customform.CustomFieldOptionVo;
import com.siyueli.platform.service.member.client.callback.customform.CustomFieldOptionFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = CustomFieldOptionFallBack.class)
public interface CustomFieldOptionClient {

    @RequestMapping(value = "/customFieldOption/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData add(@RequestBody AddCustomFieldOptionRequest requestParam);

    @RequestMapping(value = "/customFieldOption/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData update(@RequestBody UpdateCustomFieldOptionRequest requestParam);

    @RequestMapping(value = "/customFieldOption/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<CustomFieldOptionVo> get(@RequestBody GetCustomFieldOptionRequest requestParam);

    @RequestMapping(value = "/customFieldOption/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<PageResponse<CustomFieldOptionVo>> search(@RequestBody SearchCustomFieldOptionRequest requestParam);

    @RequestMapping(value = "/customFieldOption/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData delete(@RequestBody DeleteCustomFieldOptionRequest requestParam);
}
