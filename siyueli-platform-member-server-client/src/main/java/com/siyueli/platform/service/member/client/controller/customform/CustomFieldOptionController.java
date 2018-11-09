package com.siyueli.platform.service.member.client.controller.customform;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.customfieldoption.*;
import com.siyueli.platform.member.response.customform.CustomFieldOptionVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.customform.CustomFieldOptionClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_自定义表单_字段选项管理接口")
@RestController
@RequestMapping("/customFieldOption")
public class CustomFieldOptionController extends BaseController {

    @Autowired
    private CustomFieldOptionClient customFieldOptionClient;

    @ApiOperation(nickname = "addCustomFieldOption",value = "新增字段选项接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody AddCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFieldOptionClient.add(requestParam);
    }

    @ApiOperation(nickname = "updateCustomFieldOption",value = "更新字段选项接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody UpdateCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFieldOptionClient.update(requestParam);
    }

    @ApiOperation(nickname = "getCustomFieldOption",value = "得到字段选项接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<CustomFieldOptionVo> get(@Valid GetCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFieldOptionClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchCustomFieldOption",value = "搜索字段选项接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<CustomFieldOptionVo>> search(@Valid SearchCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFieldOptionClient.search(requestParam);
    }

    @ApiOperation(nickname = "deleteCustomFieldOption",value = "删除字段选项接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody DeleteCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFieldOptionClient.delete(requestParam);
    }

}
