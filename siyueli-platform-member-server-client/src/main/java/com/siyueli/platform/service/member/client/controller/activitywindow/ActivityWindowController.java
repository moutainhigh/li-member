package com.siyueli.platform.service.member.client.controller.activitywindow;


import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.activitywindow.actwincontent.*;
import com.siyueli.platform.member.request.activitywindow.actwingroup.*;
import com.siyueli.platform.member.request.activitywindow.actwinplate.*;
import com.siyueli.platform.member.response.activitywindow.ActWinContentVo;
import com.siyueli.platform.member.response.activitywindow.ActWinGroupVo;
import com.siyueli.platform.member.response.activitywindow.ActWinPlateVo;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.activitywindow.ActivityWindowClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Api(tags = "斯越里_后台_活动橱窗管理接口")
@RestController
@RequestMapping("/activityWindow")
public class ActivityWindowController extends BaseController {

    @Autowired
    private ActivityWindowClient activityWindowClient;

    @ApiOperation(nickname = "addPlate",value = "1_板块_新增活动橱窗板块")
    @LogAnnotation
    @PostMapping("/addPlate")
    public ResponseData addPlate(@RequestBody AddActWinPlateRequest requestParam, BindingResult result) {
        return activityWindowClient.addPlate(requestParam);
    }

    @ApiOperation(nickname = "updatePlate",value = "2_板块_更新活动橱窗板块")
    @LogAnnotation
    @PostMapping("/updatePlate")
    public ResponseData updatePlate(@RequestBody UpdateActWinPlateRequest requestParam, BindingResult result) {
        return activityWindowClient.updatePlate(requestParam);
    }

    @ApiOperation(nickname = "getPlate",value = "3_板块_得到活动橱窗板块")
    @LogAnnotation
    @GetMapping("/getPlate")
    public ResponseData<ActWinPlateVo> getPlate(GetActWinPlateRequest requestParam, BindingResult result) {
        return activityWindowClient.getPlate(requestParam);
    }

    @ApiOperation(nickname = "searchPlate",value = "4_板块_搜索活动橱窗板块")
    @LogAnnotation
    @GetMapping("/searchPlate")
    public ResponseData<PageResponse<ActWinPlateVo>> searchPlate(SearchActWinPlateRequest requestParam, BindingResult result) {
        return activityWindowClient.searchPlate(requestParam);
    }

    @ApiOperation(nickname = "deletePlate",value = "5_板块_删除活动橱窗板块")
    @LogAnnotation
    @PostMapping("/deletePlate")
    public ResponseData deletePlate(@RequestBody DeleteActWinPlateRequest requestParam, BindingResult result) {
        return activityWindowClient.deletePlate(requestParam);
    }

    @ApiOperation(nickname = "addGroup",value = "6_分组_新增活动橱窗分组")
    @LogAnnotation
    @PostMapping("/addGroup")
    public ResponseData addGroup(@RequestBody AddActWinGroupRequest requestParam, BindingResult result) {
        return activityWindowClient.addGroup(requestParam);
    }

    @ApiOperation(nickname = "updateGroup",value = "7_分组_更新活动橱窗分组")
    @LogAnnotation
    @PostMapping("/updateGroup")
    public ResponseData updateGroup(@RequestBody UpdateActWinGroupRequest requestParam, BindingResult result) {
        return activityWindowClient.updateGroup(requestParam);
    }

    @ApiOperation(nickname = "getGroup",value = "8_分组_得到活动橱窗分组")
    @LogAnnotation
    @GetMapping("/getGroup")
    public ResponseData<ActWinGroupVo> getGroup(GetActWinGroupRequest requestParam, BindingResult result) {
        return activityWindowClient.getGroup(requestParam);
    }

    @ApiOperation(nickname = "searchGroup",value = "9_分组_搜索活动橱窗分组")
    @LogAnnotation
    @GetMapping("/searchGroup")
    public ResponseData<PageResponse<ActWinGroupVo>> searchGroup(SearchActWinGroupRequest requestParam, BindingResult result) {
        return activityWindowClient.searchGroup(requestParam);
    }

    @ApiOperation(nickname = "deleteGroup",value = "10_分组_删除活动橱窗分组")
    @LogAnnotation
    @PostMapping("/deleteGroup")
    public ResponseData deleteGroup(@RequestBody DeleteActWinGroupRequest requestParam, BindingResult result) {
        return activityWindowClient.deleteGroup(requestParam);
    }


    @ApiOperation(nickname = "addContent",value = "11_内容_新增活动橱窗内容")
    @LogAnnotation
    @PostMapping("/addContent")
    public ResponseData addContent(@RequestBody AddActWinContentRequest requestParam, BindingResult result) {
        return activityWindowClient.addContent(requestParam);
    }

    @ApiOperation(nickname = "updateContent",value = "12_内容_更新活动橱窗内容")
    @LogAnnotation
    @PostMapping("/updateContent")
    public ResponseData updateContent(@RequestBody UpdateActWinContentRequest requestParam, BindingResult result) {
        return activityWindowClient.updateContent(requestParam);
    }

    @ApiOperation(nickname = "getContent",value = "13_内容_得到活动橱窗内容")
    @LogAnnotation
    @GetMapping("/getContent")
    public ResponseData<ActWinContentVo> getContent(GetActWinContentRequest requestParam, BindingResult result) {
        return activityWindowClient.getContent(requestParam);
    }

    @ApiOperation(nickname = "searchContent",value = "14_内容_搜索活动橱窗内容")
    @LogAnnotation
    @GetMapping("/searchContent")
    public ResponseData<PageResponse<ActWinContentVo>> searchContent(SearchActWinContentRequest requestParam, BindingResult result) {
        return activityWindowClient.searchContent(requestParam);
    }

    @ApiOperation(nickname = "deleteContent",value = "15_内容_删除活动橱窗内容")
    @LogAnnotation
    @PostMapping("/deleteContent")
    public ResponseData deleteContent(@RequestBody DeleteActWinContentRequest requestParam, BindingResult result) {
        return activityWindowClient.deleteContent(requestParam);
    }

}
