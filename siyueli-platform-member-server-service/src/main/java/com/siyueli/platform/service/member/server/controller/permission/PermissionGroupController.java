package com.siyueli.platform.service.member.server.controller.permission;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.PermissionGroup;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionGroupVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.permission.PermissionGroupServiceContract;
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

@Api(tags = "权限分组管理接口")
@RestController
@RequestMapping("/permissionGroup")
public class PermissionGroupController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionGroupController.class);

    @Autowired
    private PermissionGroupServiceContract permissionGroupService;

    @ApiOperation(nickname = "add",value = "新增权限分组接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody PermissionGroupAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionGroup entity = new PermissionGroup();
        BeanUtils.copyProperties(requestParam, entity);


        permissionGroupService.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新权限分组接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody PermissionGroupUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionGroup entity = permissionGroupService.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);


        permissionGroupService.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索权限分组接口")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody PermissionGroupSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        EntityWrapper<PermissionGroup> entityWrapper = new EntityWrapper<PermissionGroup>();
        Page<PermissionGroup> page = new Page<PermissionGroup>(requestParam.getPage(), requestParam.getSize());
        Page<PermissionGroup> resultPage = permissionGroupService.selectPage(page, entityWrapper);
        try {
            PageResponse<PermissionGroupVo> pageResponse = ConvertUtil.getPageResponse(resultPage, PermissionGroupVo.class);
            return ResponseUtil.success(pageResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResponseUtil.fail();
    }

    @ApiOperation(nickname = "get",value = "得到权限分组接口")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody PermissionGroupGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionGroup entity = permissionGroupService.selectById(requestParam.getId());
        PermissionGroupVo cffVo = null;
        if (entity != null) {
            cffVo = new PermissionGroupVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除权限分组接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody PermissionGroupDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        permissionGroupService.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
