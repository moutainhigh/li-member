package com.siyueli.platform.service.member.client.controller.customform;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.*;
import com.siyueli.platform.member.response.customform.CustomFormVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.customform.CustomFormClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_自定义表单_表单管理接口")
@RestController
@RequestMapping("/customForm")
public class CustomFormController extends BaseController {

    @Autowired
    private CustomFormClient customFormClient;

    @ApiOperation(nickname = "addCustomForm",value = "新增表单接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody CustomFormAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormClient.add(requestParam);
    }

    @ApiOperation(nickname = "updateCustomForm",value = "更新表单接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody CustomFormUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormClient.update(requestParam);
    }

    @ApiOperation(nickname = "getCustomForm",value = "得到表单接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<CustomFormVo> get(@Valid CustomFormGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchCustomForm",value = "搜索表单接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<CustomFormVo>> search(@Valid CustomFormSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormClient.search(requestParam);
    }

    @ApiOperation(nickname = "deleteCustomForm",value = "删除表单接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody CustomFormDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormClient.delete(requestParam);
    }

}
