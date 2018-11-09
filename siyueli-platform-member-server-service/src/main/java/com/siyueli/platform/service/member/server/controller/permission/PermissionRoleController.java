package com.siyueli.platform.service.member.server.controller.permission;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.PermissionRole;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionRoleVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.permission.PermissionRoleServiceContract;
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

@Api(tags = "权限角色对应管理接口")
@RestController
@RequestMapping("/permissionRole")
public class PermissionRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionRoleController.class);

    @Autowired
    private PermissionRoleServiceContract permissionRoleService;

    @ApiOperation(nickname = "add",value = "新增权限角色对应接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody PermissionRoleAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionRole entity = new PermissionRole();
        BeanUtils.copyProperties(requestParam, entity);


        permissionRoleService.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新权限角色对应接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody PermissionRoleUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionRole entity = permissionRoleService.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);


        permissionRoleService.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索权限角色对应接口")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody PermissionRoleSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        EntityWrapper<PermissionRole> entityWrapper = new EntityWrapper<PermissionRole>();
        Page<PermissionRole> page = new Page<PermissionRole>(requestParam.getPage(), requestParam.getSize());
        Page<PermissionRole> resultPage = permissionRoleService.selectPage(page, entityWrapper);
        try {
            PageResponse<PermissionRoleVo> pageResponse = ConvertUtil.getPageResponse(resultPage, PermissionRoleVo.class);
            return ResponseUtil.success(pageResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResponseUtil.fail();
    }

    @ApiOperation(nickname = "get",value = "得到权限角色对应接口")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody PermissionRoleGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionRole entity = permissionRoleService.selectById(requestParam.getId());
        PermissionRoleVo cffVo = null;
        if (entity != null) {
            cffVo = new PermissionRoleVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除权限角色对应接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody PermissionRoleDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        permissionRoleService.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
