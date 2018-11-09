package com.siyueli.platform.service.member.server.controller.customform;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.pojo.customform.CustomFormCategory;
import com.siyueli.platform.member.pojo.customform.CustomFormField;
import com.siyueli.platform.member.request.customform.customformcategory.*;
import com.siyueli.platform.member.request.customform.customformfield.*;
import com.siyueli.platform.member.response.customform.CustomFormCategoryVo;
import com.siyueli.platform.member.response.customform.CustomFormFieldVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.customform.CustomFormCategoryServiceContract;
import com.siyueli.platform.service.member.server.service.customform.CustomFormFieldServiceContract;
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

@Api(tags = "斯越里_后台_自定义表单分类管理接口")
@RestController
@RequestMapping("/customFormCategory")
public class CustomFormCategoryController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFormCategoryController.class);

    @Autowired
    private CustomFormCategoryServiceContract customFormCategoryServiceContract;

    @ApiOperation(nickname = "add",value = "新增自定义表单字段")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody AddCustomFormCategoryRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFormCategory entity = new CustomFormCategory();
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setCreateAt(now);
        entity.setUpdateAt(now);

        customFormCategoryServiceContract.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新自定义表单字段")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody UpdateCustomFormCategoryRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFormCategory entity = customFormCategoryServiceContract.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setUpdateAt(now);

        customFormCategoryServiceContract.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索自定义表单字段")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody SearchCustomFormCategoryRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        return customFormCategoryServiceContract.search(requestParam);
    }

    @ApiOperation(nickname = "get",value = "得到自定义表单字段")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody GetCustomFormCategoryRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFormCategory entity = customFormCategoryServiceContract.selectById(requestParam.getId());
        CustomFormCategoryVo cffVo = null;
        if (entity != null) {
            cffVo = new CustomFormCategoryVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除自定义表单字段")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody DeleteCustomFormCategoryRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        customFormCategoryServiceContract.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
