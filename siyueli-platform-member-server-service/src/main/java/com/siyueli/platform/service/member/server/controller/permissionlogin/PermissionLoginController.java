package com.siyueli.platform.service.member.server.controller.permissionlogin;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.pojo.permissionlogin.PermissionLogin;
import com.siyueli.platform.member.request.permissionlogin.*;
import com.siyueli.platform.member.response.permissionlogin.PermissionLoginVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.permissionlogin.PermissionLoginServiceContract;
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

@Api(tags = "访问的uri是否需要登录管理接口")
@RestController
@RequestMapping("/permissionLogin")
public class PermissionLoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionLoginController.class);

    @Autowired
    private PermissionLoginServiceContract permissionLoginService;

    @ApiOperation(nickname = "add",value = "新增访问的uri是否需要登录接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody PermissionLoginAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionLogin entity = new PermissionLogin();
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setCreateAt(now);
        entity.setUpdateAt(now);

        permissionLoginService.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新访问的uri是否需要登录接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody PermissionLoginUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionLogin entity = permissionLoginService.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setUpdateAt(now);

        permissionLoginService.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索访问的uri是否需要登录接口")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody PermissionLoginSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        return permissionLoginService.search(requestParam);
    }

    @ApiOperation(nickname = "get",value = "得到访问的uri是否需要登录接口")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody PermissionLoginGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        PermissionLogin entity = permissionLoginService.selectById(requestParam.getId());
        PermissionLoginVo cffVo = null;
        if (entity != null) {
            cffVo = new PermissionLoginVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除访问的uri是否需要登录接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody PermissionLoginDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        permissionLoginService.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
