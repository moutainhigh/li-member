package com.siyueli.platform.service.member.client.controller.permission;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionGroupVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.permission.PermissionGroupClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_权限_权限分组管理接口")
@RestController
@RequestMapping("/permissionGroup")
public class PermissionGroupController extends BaseController {

    @Autowired
    private PermissionGroupClient permissionGroupClient;

    @ApiOperation(nickname = "addPermissionGroup",value = "新增权限分组接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody PermissionGroupAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionGroupClient.add(requestParam);
    }

    @ApiOperation(nickname = "updatePermissionGroup",value = "更新权限分组接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody PermissionGroupUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionGroupClient.update(requestParam);
    }

    @ApiOperation(nickname = "getPermissionGroup",value = "得到权限分组接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<PermissionGroupVo> get(@Valid PermissionGroupGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionGroupClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchPermissionGroup",value = "搜索权限分组接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<PermissionGroupVo>> search(@Valid PermissionGroupSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionGroupClient.search(requestParam);
    }

    @ApiOperation(nickname = "deletePermissionGroup",value = "删除权限分组接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody PermissionGroupDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionGroupClient.delete(requestParam);
    }

}
