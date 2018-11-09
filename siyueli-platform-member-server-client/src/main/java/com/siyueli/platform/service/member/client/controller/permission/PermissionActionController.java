package com.siyueli.platform.service.member.client.controller.permission;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.PermissionActionVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.permission.PermissionActionClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_权限_权限管理接口")
@RestController
@RequestMapping("/permissionAction")
public class PermissionActionController extends BaseController {

    @Autowired
    private PermissionActionClient permissionActionClient;

    @ApiOperation(nickname = "addPermissionAction",value = "新增权限接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody PermissionActionAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionActionClient.add(requestParam);
    }

    @ApiOperation(nickname = "updatePermissionAction",value = "更新权限接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody PermissionActionUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionActionClient.update(requestParam);
    }

    @ApiOperation(nickname = "getPermissionAction",value = "得到权限接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<PermissionActionVo> get(@Valid PermissionActionGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionActionClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchPermissionAction",value = "搜索权限接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<PermissionActionVo>> search(@Valid PermissionActionSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionActionClient.search(requestParam);
    }

    @ApiOperation(nickname = "deletePermissionAction",value = "删除权限接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody PermissionActionDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return permissionActionClient.delete(requestParam);
    }

}
