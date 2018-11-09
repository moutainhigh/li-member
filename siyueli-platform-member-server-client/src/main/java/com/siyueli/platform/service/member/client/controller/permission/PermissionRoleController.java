package com.siyueli.platform.service.member.client.controller.permission;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionRoleVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.permission.PermissionRoleClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_权限_权限角色对应管理接口")
@RestController
@RequestMapping("/permissionRole")
public class PermissionRoleController extends BaseController {

    @Autowired
    private PermissionRoleClient permissionRoleClient;

    @ApiOperation(nickname = "addPermissionRole",value = "新增权限角色对应接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody PermissionRoleAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionRoleClient.add(requestParam);
    }

    @ApiOperation(nickname = "updatePermissionRole",value = "更新权限角色对应接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody PermissionRoleUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionRoleClient.update(requestParam);
    }

    @ApiOperation(nickname = "getPermissionRole",value = "得到权限角色对应接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<PermissionRoleVo> get(@Valid PermissionRoleGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionRoleClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchPermissionRole",value = "搜索权限角色对应接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<PermissionRoleVo>> search(@Valid PermissionRoleSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionRoleClient.search(requestParam);
    }

    @ApiOperation(nickname = "deletePermissionRole",value = "删除权限角色对应接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody PermissionRoleDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionRoleClient.delete(requestParam);
    }

}
