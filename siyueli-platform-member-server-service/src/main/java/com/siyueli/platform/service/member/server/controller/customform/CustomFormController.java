package com.siyueli.platform.service.member.server.controller.customform;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.pojo.customform.CustomForm;
import com.siyueli.platform.member.pojo.customform.CustomFormFieldRelationship;
import com.siyueli.platform.member.request.customform.*;
import com.siyueli.platform.member.response.customform.CustomFormVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.customform.CustomFormFieldRelationshipServiceContract;
import com.siyueli.platform.service.member.server.service.customform.CustomFormServiceContract;
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
import java.util.List;

@Api(tags = "表单管理接口")
@RestController
@RequestMapping("/customForm")
public class CustomFormController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFormController.class);

    @Autowired
    private CustomFormServiceContract customFormService;

    @Autowired
    private CustomFormFieldRelationshipServiceContract customFormFieldRelationshipService;

    @ApiOperation(nickname = "add",value = "新增表单接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody CustomFormAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomForm entity = new CustomForm();
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setCreateAt(now);
        entity.setUpdateAt(now);

        customFormService.insertAllColumn(entity);
        addFields(requestParam, entity.getId());

        return ResponseUtil.success();
    }

    private void addFields(CustomFormAddRequest customFormAddRequest, Long formId) {
        List<CustomFormAddFieldVo> fields = customFormAddRequest.getFields();
        CustomFormFieldRelationship entity = new CustomFormFieldRelationship();

        if (fields != null && fields.size() > 0) {
            for (CustomFormAddFieldVo field : fields) {
                BeanUtils.copyProperties(field, entity);
                entity.setFormId(formId);
                Date now = new Date();
                entity.setCreateAt(now);
                entity.setUpdateAt(now);

                customFormFieldRelationshipService.insertAllColumn(entity);
            }
        }

    }

    @ApiOperation(nickname = "update",value = "更新表单接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody CustomFormUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomForm entity = customFormService.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setUpdateAt(now);

        customFormService.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索表单接口")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody CustomFormSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        return customFormService.search(requestParam);
    }

    @ApiOperation(nickname = "get",value = "得到表单接口")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody CustomFormGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomForm entity = customFormService.selectById(requestParam.getId());
        CustomFormVo cffVo = null;
        if (entity != null) {
            cffVo = new CustomFormVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除表单接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody CustomFormDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        customFormService.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
