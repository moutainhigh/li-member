package com.siyueli.platform.service.member.client.controller.customform;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.customformfield.*;
import com.siyueli.platform.member.response.customform.CustomFormFieldVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.customform.CustomFormFieldClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_自定义表单_表单字段管理接口")
@RestController
@RequestMapping("/customFormField")
public class CustomFormFieldController extends BaseController {

    @Autowired
    private CustomFormFieldClient customFormFieldClient;

    @ApiOperation(nickname = "addCustomFormField",value = "新增表单字段接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody AddCustomFormFieldRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldClient.add(requestParam);
    }

    @ApiOperation(nickname = "updateCustomFormField",value = "更新表单字段接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody UpdateCustomFormFieldRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldClient.update(requestParam);
    }

    @ApiOperation(nickname = "getCustomFormField",value = "得到表单字段接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<CustomFormFieldVo> get(@Valid GetCustomFormFieldRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchCustomFormField",value = "搜索表单字段接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<CustomFormFieldVo>> search(@Valid SearchCustomFormFieldRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldClient.search(requestParam);
    }

    @ApiOperation(nickname = "deleteCustomFormField",value = "删除表单字段接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody DeleteCustomFormFieldRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldClient.delete(requestParam);
    }

}
