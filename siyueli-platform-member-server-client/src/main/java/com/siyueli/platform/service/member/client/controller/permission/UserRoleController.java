package com.siyueli.platform.service.member.client.controller.permission;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.UserRoleVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.permission.UserRoleClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "斯越里_后台_权限_用户角色管理接口")
@RestController
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleClient userRoleClient;

    @ApiOperation(nickname = "addUserRole",value = "新增用户角色接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody UserRoleAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return userRoleClient.add(requestParam);
    }

    @ApiOperation(nickname = "updateUserRole",value = "更新用户角色接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody UserRoleUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return userRoleClient.update(requestParam);
    }

    @ApiOperation(nickname = "getUserRole",value = "得到用户角色接口")
    @LogAnnotation
    @GetMapping("/get")
    public ResponseData<UserRoleVo> get(@Valid UserRoleGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return userRoleClient.get(requestParam);
    }

    @ApiOperation(nickname = "searchUserRole",value = "搜索用户角色接口")
    @LogAnnotation
    @GetMapping("/search")
    public ResponseData<PageResponse<UserRoleVo>> search(@Valid UserRoleSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return userRoleClient.search(requestParam);
    }

    @ApiOperation(nickname = "deleteUserRole",value = "删除用户角色接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody UserRoleDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }
        return userRoleClient.delete(requestParam);
    }

}
