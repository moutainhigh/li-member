package com.siyueli.platform.service.member.server.controller.activitywindow;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.pojo.activitywindow.ActivityWindowContent;
import com.siyueli.platform.member.pojo.activitywindow.ActivityWindowGroup;
import com.siyueli.platform.member.pojo.activitywindow.ActivityWindowPlate;
import com.siyueli.platform.member.request.activitywindow.actwincontent.*;
import com.siyueli.platform.member.request.activitywindow.actwingroup.*;
import com.siyueli.platform.member.request.activitywindow.actwinplate.*;
import com.siyueli.platform.member.response.activitywindow.ActWinContentVo;
import com.siyueli.platform.member.response.activitywindow.ActWinGroupVo;
import com.siyueli.platform.member.response.activitywindow.ActWinPlateVo;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.activitywindow.ActivityWindowContentServiceContract;
import com.siyueli.platform.service.member.server.service.activitywindow.ActivityWindowGroupServiceContract;
import com.siyueli.platform.service.member.server.service.activitywindow.ActivityWindowPlateServiceContract;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "斯越里_后台_活动橱窗管理接口")
@RestController
@RequestMapping("/activityWindow")
public class ActivityWindowController extends BaseController {

    @Autowired
    private ActivityWindowContentServiceContract activityWindowContentServiceContract;

    @Autowired
    private ActivityWindowGroupServiceContract activityWindowGroupServiceContract;

    @Autowired
    private ActivityWindowPlateServiceContract activityWindowPlateServiceContract;


    @ApiOperation(nickname = "addPlate",value = "新增活动橱窗板块")
    @LogAnnotation
    @PostMapping("/addPlate")
    public ResponseData addPlate(@RequestBody AddActWinPlateRequest requestParam, BindingResult result) {
        ActivityWindowPlate entity = new ActivityWindowPlate();
        BeanUtils.copyProperties(requestParam, entity);

        Date now = new Date();
        entity.setStatus(1);
        entity.setCreateAt(now);
        entity.setUpdateAt(now);

        activityWindowPlateServiceContract.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "updatePlate",value = "更新活动橱窗板块")
    @LogAnnotation
    @PostMapping("/updatePlate")
    public ResponseData updatePlate(@RequestBody UpdateActWinPlateRequest requestParam, BindingResult result) {
        ActivityWindowPlate entity = activityWindowPlateServiceContract.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);
        Date now = new Date();
        entity.setUpdateAt(now);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "getPlate",value = "得到活动橱窗板块")
    @LogAnnotation
    @PostMapping("/getPlate")
    public ResponseData getPlate(@RequestBody GetActWinPlateRequest requestParam, BindingResult result) {
        ActivityWindowPlate entity = activityWindowPlateServiceContract.selectById(requestParam.getId());
        ActWinPlateVo actWinPlateVo = new ActWinPlateVo();
        BeanUtils.copyProperties(entity, actWinPlateVo);
        return ResponseUtil.success(actWinPlateVo);
    }

    @ApiOperation(nickname = "searchPlate",value = "搜索活动橱窗板块")
    @LogAnnotation
    @PostMapping("/searchPlate")
    public ResponseData searchPlate(@RequestBody SearchActWinPlateRequest requestParam, BindingResult result) {
        return null;
    }

    @ApiOperation(nickname = "deletePlate",value = "删除活动橱窗板块")
    @LogAnnotation
    @PostMapping("/deletePlate")
    public ResponseData deletePlate(@RequestBody DeleteActWinPlateRequest requestParam, BindingResult result) {
        activityWindowPlateServiceContract.deleteById(requestParam.getId());

        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "addGroup",value = "新增活动橱窗分组")
    @LogAnnotation
    @PostMapping("/addGroup")
    public ResponseData addGroup(@RequestBody AddActWinGroupRequest requestParam, BindingResult result) {
        ActivityWindowGroup entity = new ActivityWindowGroup();
        BeanUtils.copyProperties(requestParam, entity);
        Date now = new Date();
        entity.setStatus(1);
        entity.setCreateAt(now);
        entity.setUpdateAt(now);
        activityWindowGroupServiceContract.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "updateGroup",value = "更新活动橱窗分组")
    @LogAnnotation
    @PostMapping("/updateGroup")
    public ResponseData updateGroup(@RequestBody UpdateActWinGroupRequest requestParam, BindingResult result) {
        ActivityWindowGroup entity = activityWindowGroupServiceContract.selectById(requestParam.getId());
        BeanUtils.copyProperties(requestParam, entity);
        Date now = new Date();
        entity.setUpdateAt(now);
        activityWindowGroupServiceContract.updateAllColumnById(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "getGroup",value = "得到活动橱窗分组")
    @LogAnnotation
    @PostMapping("/getGroup")
    public ResponseData getGroup(@RequestBody GetActWinGroupRequest requestParam, BindingResult result) {
        ActivityWindowGroup entity = activityWindowGroupServiceContract.selectById(requestParam.getId());
        ActWinGroupVo groupVo = null;
        if (entity != null) {
            groupVo = new ActWinGroupVo();
            BeanUtils.copyProperties(entity, groupVo);
        }
        return ResponseUtil.success(groupVo);
    }

    @ApiOperation(nickname = "searchGroup",value = "搜索活动橱窗分组")
    @LogAnnotation
    @PostMapping("/searchGroup")
    public ResponseData searchGroup(@RequestBody SearchActWinGroupRequest requestParam, BindingResult result) {
        return null;
    }

    @ApiOperation(nickname = "deleteGroup",value = "删除活动橱窗分组")
    @LogAnnotation
    @PostMapping("/deleteGroup")
    public ResponseData deleteGroup(@RequestBody DeleteActWinGroupRequest requestParam, BindingResult result) {
        activityWindowGroupServiceContract.deleteById(requestParam.getId());
        return ResponseUtil.success();
    }


    @ApiOperation(nickname = "addContent",value = "新增活动橱窗内容")
    @LogAnnotation
    @PostMapping("/addContent")
    public ResponseData addContent(@RequestBody AddActWinContentRequest requestParam, BindingResult result) {
        ActivityWindowContent entity = new ActivityWindowContent();
        BeanUtils.copyProperties(requestParam, entity);
        Date now = new Date();
        entity.setStatus(1);
        entity.setCreateAt(now);
        entity.setUpdateAt(now);
        activityWindowContentServiceContract.insertAllColumn(entity);
        return ResponseUtil.success();
    }

    @ApiOperation(nickname = "updateContent",value = "更新活动橱窗内容")
    @LogAnnotation
    @PostMapping("/updateContent")
    public ResponseData updateContent(@RequestBody UpdateActWinContentRequest requestParam, BindingResult result) {
        ActivityWindowContent entity = activityWindowContentServiceContract.selectById(requestParam.getId());
        if (entity != null) {
            BeanUtils.copyProperties(requestParam, entity);
            Date now = new Date();
            entity.setUpdateAt(now);
            activityWindowContentServiceContract.updateAllColumnById(entity);
            return ResponseUtil.success();
        }

        return ResponseUtil.fail();
    }

    @ApiOperation(nickname = "getContent",value = "得到活动橱窗内容")
    @LogAnnotation
    @PostMapping("/getContent")
    public ResponseData getContent(@RequestBody GetActWinContentRequest requestParam, BindingResult result) {
        ActivityWindowContent entity = activityWindowContentServiceContract.selectById(requestParam.getId());
        ActWinContentVo contentVo = null;
        if (entity != null) {
            BeanUtils.copyProperties(entity, contentVo);
        }
        return ResponseUtil.success(contentVo);
    }

    @ApiOperation(nickname = "searchContent",value = "搜索活动橱窗内容")
    @LogAnnotation
    @PostMapping("/searchContent")
    public ResponseData searchContent(@RequestBody SearchActWinContentRequest requestParam, BindingResult result) {
        return null;
    }

    @ApiOperation(nickname = "deleteContent",value = "删除活动橱窗内容")
    @LogAnnotation
    @PostMapping("/deleteContent")
    public ResponseData deleteContent(@RequestBody DeleteActWinContentRequest requestParam, BindingResult result) {
        activityWindowContentServiceContract.deleteById(requestParam.getId());

        return ResponseUtil.success();
    }

}
