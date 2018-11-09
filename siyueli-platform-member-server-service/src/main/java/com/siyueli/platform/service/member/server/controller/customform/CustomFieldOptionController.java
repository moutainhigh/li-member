package com.siyueli.platform.service.member.server.controller.customform;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.pojo.customform.CustomFieldOption;
import com.siyueli.platform.member.request.customform.customfieldoption.*;
import com.siyueli.platform.member.response.customform.CustomFieldOptionVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.customform.CustomFieldOptionServiceContract;
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

@Api(tags = "斯越里_后台_自定义表单字段选项管理接口")
@RestController
@RequestMapping("/customFieldOption")
public class CustomFieldOptionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFieldOptionController.class);

    @Autowired
    private CustomFieldOptionServiceContract customFieldOptionServiceContract;

    @ApiOperation(nickname = "add",value = "新增自定义表单字段选项")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody AddCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFieldOption entity = new CustomFieldOption();
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setCreateAt(now);
        entity.setUpdateAt(now);

        customFieldOptionServiceContract.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新自定义表单字段选项")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody UpdateCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFieldOption entity = customFieldOptionServiceContract.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setUpdateAt(now);

        customFieldOptionServiceContract.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索自定义表单字段选项")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody SearchCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        return customFieldOptionServiceContract.search(requestParam);
    }

    @ApiOperation(nickname = "get",value = "得到自定义表单字段选项")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody GetCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        CustomFieldOption entity = customFieldOptionServiceContract.selectById(requestParam.getId());
        CustomFieldOptionVo cffVo = null;
        if (entity != null) {
            cffVo = new CustomFieldOptionVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除自定义表单字段选项")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody DeleteCustomFieldOptionRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        customFieldOptionServiceContract.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
