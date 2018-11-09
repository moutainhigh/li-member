package com.siyueli.platform.service.member.server.controller.customform;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.pojo.customform.CustomFormFieldRelationship;
import com.siyueli.platform.member.request.customform.*;
import com.siyueli.platform.member.response.customform.CustomFormFieldRelationshipVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.customform.CustomFormFieldRelationshipServiceContract;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@Api(tags = "表单与字段关系管理接口")
@RestController
@RequestMapping("/customFormFieldRelationship")
public class CustomFormFieldRelationshipController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFormFieldRelationshipController.class);

    @Autowired
    private CustomFormFieldRelationshipServiceContract customFormFieldRelationshipService;

    @ApiOperation(nickname = "add",value = "新增表单与字段关系接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody CustomFormFieldRelationshipAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFormFieldRelationship entity = new CustomFormFieldRelationship();
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setCreateAt(now);
        entity.setUpdateAt(now);

        customFormFieldRelationshipService.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新表单与字段关系接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody CustomFormFieldRelationshipUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFormFieldRelationship entity = customFormFieldRelationshipService.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setUpdateAt(now);

        customFormFieldRelationshipService.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索表单与字段关系接口")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody CustomFormFieldRelationshipSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        return customFormFieldRelationshipService.search(requestParam);
    }

    @ApiOperation(nickname = "get",value = "得到表单与字段关系接口")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody CustomFormFieldRelationshipGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFormFieldRelationship entity = customFormFieldRelationshipService.selectById(requestParam.getId());
        CustomFormFieldRelationshipVo cffVo = null;
        if (entity != null) {
            cffVo = new CustomFormFieldRelationshipVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除表单与字段关系接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody CustomFormFieldRelationshipDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        customFormFieldRelationshipService.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
