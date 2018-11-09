package com.siyueli.platform.service.member.server.controller.permission;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.Role;
import com.siyueli.platform.member.request.*;
import com.siyueli.platform.member.response.RoleVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.permission.RoleServiceContract;
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

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleServiceContract roleService;

    @ApiOperation(nickname = "add",value = "新增角色接口")
    @LogAnnotation
    @PostMapping("/add")
    public ResponseData add(@Valid @RequestBody RoleAddRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        Role entity = new Role();
        BeanUtils.copyProperties(requestParam, entity);


        roleService.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "update",value = "更新角色接口")
    @LogAnnotation
    @PostMapping("/update")
    public ResponseData update(@Valid @RequestBody RoleUpdateRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        Role entity = roleService.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);


        roleService.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "search",value = "搜索角色接口")
    @LogAnnotation
    @PostMapping("/search")
    public ResponseData search(@Valid @RequestBody RoleSearchRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        EntityWrapper<Role> entityWrapper = new EntityWrapper<Role>();
        Page<Role> page = new Page<Role>(requestParam.getPage(), requestParam.getSize());
        Page<Role> resultPage = roleService.selectPage(page, entityWrapper);
        try {
            PageResponse<RoleVo> pageResponse = ConvertUtil.getPageResponse(resultPage, RoleVo.class);
            return ResponseUtil.success(pageResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResponseUtil.fail();
    }

    @ApiOperation(nickname = "get",value = "得到角色接口")
    @LogAnnotation
    @PostMapping("/get")
    public ResponseData get(@Valid @RequestBody RoleGetRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        Role entity = roleService.selectById(requestParam.getId());
        RoleVo cffVo = null;
        if (entity != null) {
            cffVo = new RoleVo();
            BeanUtils.copyProperties(entity, cffVo);
        }
        return ResponseUtil.success(cffVo);
    }

    @ApiOperation(nickname = "delete",value = "删除角色接口")
    @LogAnnotation
    @PostMapping("/delete")
    public ResponseData delete(@Valid @RequestBody RoleDeleteRequest requestParam, BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return getErrorResponse(result);
        }

        roleService.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }
}
