package com.siyueli.platform.service.member.server.controller.permission;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.PermissionAction;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionActionVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.permission.PermissionActionServiceContract;
import com.siyueli.platform.service.member.server.util.ConvertUtil;
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

@Api(tags = "权限管理接口")
@RestController
@RequestMapping("/permissionAction")
public class PermissionActionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionActionController.class);

    @Autowired
    private PermissionActionServiceContract permissionActionService;

    @ApiOperation(nickname = "add",value = "新增权限接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody PermissionActionAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionAction entity = new PermissionAction();
        BeanUtils.copyProperties(requestParam, entity);

        permissionActionService.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新权限接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody PermissionActionUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionAction entity = permissionActionService.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);

        permissionActionService.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索权限接口")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody PermissionActionSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        EntityWrapper<PermissionAction> entityWrapper = new EntityWrapper<PermissionAction>();
        Page<PermissionAction> page = new Page<PermissionAction>(requestParam.getPage(), requestParam.getSize());
        Page<PermissionAction> resultPage = permissionActionService.selectPage(page, entityWrapper);
        try {
            PageResponse<PermissionActionVo> pageResponse = ConvertUtil.getPageResponse(resultPage, PermissionActionVo.class);
            return ResponseUtil.success(pageResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResponseUtil.fail();
    }

    @ApiOperation(nickname = "get",value = "得到权限接口")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody PermissionActionGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionAction entity = permissionActionService.selectById(requestParam.getId());
        PermissionActionVo cffVo = null;
        if (entity != null) {
            cffVo = new PermissionActionVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除权限接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody PermissionActionDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        permissionActionService.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
