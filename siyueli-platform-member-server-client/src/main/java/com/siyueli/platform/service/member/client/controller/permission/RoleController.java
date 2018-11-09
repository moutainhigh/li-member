package com.siyueli.platform.service.member.client.controller.permission;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.RoleVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.permission.RoleClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_权限_角色管理接口")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleClient roleClient;

    @ApiOperation(nickname = "addRole",value = "新增角色接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody RoleAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return roleClient.add(requestParam);
    }

    @ApiOperation(nickname = "updateRole",value = "更新角色接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody RoleUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return roleClient.update(requestParam);
    }

    @ApiOperation(nickname = "getRole",value = "得到角色接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<RoleVo> get(@Valid RoleGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return roleClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchRole",value = "搜索角色接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<RoleVo>> search(@Valid RoleSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return roleClient.search(requestParam);
    }

    @ApiOperation(nickname = "deleteRole",value = "删除角色接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody RoleDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return roleClient.delete(requestParam);
    }

}
