package com.siyueli.platform.service.member.server.controller.permission;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.UserRole;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.UserRoleVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.permission.UserRoleServiceContract;
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

@Api(tags = "用户角色管理接口")
@RestController
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleServiceContract userRoleService;

    @ApiOperation(nickname = "add",value = "新增用户角色接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody UserRoleAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        UserRole entity = new UserRole();
        BeanUtils.copyProperties(requestParam, entity);


        userRoleService.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新用户角色接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody UserRoleUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        UserRole entity = userRoleService.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);


        userRoleService.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索用户角色接口")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody UserRoleSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        EntityWrapper<UserRole> entityWrapper = new EntityWrapper<UserRole>();
        Page<UserRole> page = new Page<UserRole>(requestParam.getPage(), requestParam.getSize());
        Page<UserRole> resultPage = userRoleService.selectPage(page, entityWrapper);
        try {
            PageResponse<UserRoleVo> pageResponse = ConvertUtil.getPageResponse(resultPage, UserRoleVo.class);
            return ResponseUtil.success(pageResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResponseUtil.fail();
    }

    @ApiOperation(nickname = "get",value = "得到用户角色接口")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody UserRoleGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        UserRole entity = userRoleService.selectById(requestParam.getId());
        UserRoleVo cffVo = null;
        if (entity != null) {
            cffVo = new UserRoleVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除用户角色接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody UserRoleDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        userRoleService.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
