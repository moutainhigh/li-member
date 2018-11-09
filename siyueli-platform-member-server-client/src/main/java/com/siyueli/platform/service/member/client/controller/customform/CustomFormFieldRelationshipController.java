package com.siyueli.platform.service.member.client.controller.customform;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.customform.*;
import com.siyueli.platform.member.response.customform.CustomFormFieldRelationshipVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.customform.CustomFormFieldRelationshipClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_自定义表单_表单与字段关系管理接口")
@RestController
@RequestMapping("/customFormFieldRelationship")
public class CustomFormFieldRelationshipController extends BaseController {

    @Autowired
    private CustomFormFieldRelationshipClient customFormFieldRelationshipClient;

    @ApiOperation(nickname = "addCustomFormFieldRelationship",value = "新增表单与字段关系接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody CustomFormFieldRelationshipAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldRelationshipClient.add(requestParam);
    }

    @ApiOperation(nickname = "updateCustomFormFieldRelationship",value = "更新表单与字段关系接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody CustomFormFieldRelationshipUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldRelationshipClient.update(requestParam);
    }

    @ApiOperation(nickname = "getCustomFormFieldRelationship",value = "得到表单与字段关系接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<CustomFormFieldRelationshipVo> get(@Valid CustomFormFieldRelationshipGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldRelationshipClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchCustomFormFieldRelationship",value = "搜索表单与字段关系接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<CustomFormFieldRelationshipVo>> search(@Valid CustomFormFieldRelationshipSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldRelationshipClient.search(requestParam);
    }

    @ApiOperation(nickname = "deleteCustomFormFieldRelationship",value = "删除表单与字段关系接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody CustomFormFieldRelationshipDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return customFormFieldRelationshipClient.delete(requestParam);
    }

}
