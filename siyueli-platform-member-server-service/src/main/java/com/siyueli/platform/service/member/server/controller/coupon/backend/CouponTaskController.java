package com.siyueli.platform.service.member.server.controller.coupon.backend;

import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.coupon.Coupon;
import com.siyueli.platform.member.pojo.coupon.CouponTask;
import com.siyueli.platform.member.pojo.coupon.CouponTaskChannel;
import com.siyueli.platform.member.pojo.coupon.CouponTaskGift;
import com.siyueli.platform.member.pojo.coupon.CouponTaskSku;
import com.siyueli.platform.member.request.coupon.CouponTaskListRequest;
import com.siyueli.platform.member.request.coupon.CouponTaskRequest;
import com.siyueli.platform.member.request.coupon.GetCouponTaskListRequest;
import com.siyueli.platform.member.response.coupon.CouponTaskDetailsResponse;
import com.siyueli.platform.member.response.coupon.CouponTaskResponse;
import com.siyueli.platform.member.response.coupontask.GetCouponTaskListResponse;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.member.constants.coupon.CouponTaskStatus;
import com.siyueli.platform.service.member.server.service.coupon.CouponServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskChannelServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskGiftServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskSkuServiceContract;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 卡券任务 前端控制器
 * </p>
 */
@RestController
@RequestMapping(path = "/coupontask", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CouponTaskController {

  private CouponTaskServiceContract couponTaskService;

  private CouponServiceContract couponService;

  private CouponTaskSkuServiceContract taskSkuService;

  private CouponTaskGiftServiceContract taskGiftService;

  private CouponTaskChannelServiceContract couponTaskChannelService;

  @Autowired
  public CouponTaskController(
      CouponTaskServiceContract couponTaskService, CouponServiceContract couponService,
      CouponTaskSkuServiceContract taskSkuService,
      CouponTaskGiftServiceContract taskGiftService,
      CouponTaskChannelServiceContract couponTaskChannelService
  ) {
    this.couponTaskService = couponTaskService;
    this.couponService = couponService;
    this.taskSkuService = taskSkuService;
    this.taskGiftService = taskGiftService;
    this.couponTaskChannelService = couponTaskChannelService;
  }

  @ApiOperation(nickname = "getCouponTasks", value = "获取卡券列表")
  @GetMapping(value = "/list")
  public ResponseData<Page<CouponTaskResponse>> index(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      CouponTaskListRequest couponTaskListRequest
  ) {

    Page<CouponTaskResponse> couponTaskResponsePage = new Page<>(page, size);
    couponTaskResponsePage.setAsc(false);
    couponTaskResponsePage = couponTaskService.selectTaskPage(couponTaskResponsePage, couponTaskListRequest);

    return new ResponseData<Page<CouponTaskResponse>>(
        ResponseBackCode.SUCCESS.getValue(),
        ResponseBackCode.SUCCESS.getMessage(),
        couponTaskResponsePage
    );
  }



  @ApiOperation(nickname = "showCouponTask", value = "展示卡券任务")
  @GetMapping("/{taskId}")
  public ResponseData show(
      @PathVariable(value = "taskId") Long taskId,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    CouponTask task = couponTaskService.selectById(taskId);
    if (task == null) {
      return new ResponseData<>(
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage()
      );
    }


    CouponTaskDetailsResponse taskDetailsResponse = new CouponTaskDetailsResponse();


    BeanUtils.copyProperties(task, taskDetailsResponse);

    // 渠道
    List<CouponTaskChannel> channelList = couponTaskChannelService.selectList(new EntityWrapper<>(new CouponTaskChannel(taskId)));
    List<Integer> stringChannels = channelList.stream().map(it -> it.getChannelId()).collect(Collectors.toList());
    taskDetailsResponse.setChannelList(stringChannels);

    // sku列表
    List<CouponTaskSku> skus = taskSkuService.selectList(new EntityWrapper<>(new CouponTaskSku(taskId)));
    taskDetailsResponse.setCouponTaskSkus(skus);

    // 赠品列表
    List<CouponTaskGift> gifts = taskGiftService.selectList(new EntityWrapper<>(new CouponTaskGift(taskId)));
    taskDetailsResponse.setCouponTaskGifts(gifts);

    Page<Coupon> couponPage = new Page<>(page, size);
    couponPage.setAsc(false);

    couponPage = couponService.selectPage(couponPage, new EntityWrapper<>(new Coupon(taskId)));
    taskDetailsResponse.setCoupons(couponPage);

    return new ResponseData<CouponTaskDetailsResponse>(
        ResponseBackCode.SUCCESS.getValue(),
        ResponseBackCode.SUCCESS.getMessage(),
        taskDetailsResponse
    );
  }

  @ApiOperation(nickname = "createCouponTask", value = "新建卡券任务")
  @PostMapping("/create")
  public ResponseData<CouponTaskResponse> store(@RequestBody CouponTaskRequest couponTaskRequest) {

    try {
      CouponTask task = couponTaskService.createEntity(couponTaskRequest);

      CouponTaskResponse taskResponse = new CouponTaskResponse();

      BeanUtils.copyProperties(task, taskResponse);

      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.SUCCESS.getValue(),
          ResponseBackCode.SUCCESS.getMessage(),
          taskResponse
      );
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
          e.getMessage()
      );
    }
  }

  @ApiOperation(nickname = "updateCouponTask", value = "更新卡券任务")
  @PutMapping("/{taskId}")
  public ResponseData<CouponTaskResponse> update(@PathVariable String taskId, @RequestBody CouponTaskRequest couponTaskRequest) {

    CouponTask task = couponTaskService.selectById(taskId);
    if (task == null) {
      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
          "卡券不存在"
      );
    }

    if (!CouponTaskStatus.NOT_ENABLE.getValue().equals(task.getStatusId())) {
      // 如果不在 "未启用状态" ，不可编辑
      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_UPDATE_FAIL.getValue(),
          "卡券只有在未启用状态才能编辑"
      );
    }

    CouponTask updatedTask = couponTaskService.updateEntity(task, couponTaskRequest);

    CouponTaskResponse taskResponse = new CouponTaskResponse();

    BeanUtils.copyProperties(updatedTask, taskResponse);

    return new ResponseData<CouponTaskResponse>(
        ResponseBackCode.SUCCESS.getValue(),
        ResponseBackCode.SUCCESS.getMessage(),
        taskResponse
    );
  }

  @ApiOperation(nickname = "startCouponTask", value = "启用/禁用卡券")
  @PutMapping("/switching/{taskId}")
  public ResponseData switchStatus(
      @PathVariable(value = "taskId") Long taskId,
      @RequestParam(value = "status") Integer status
  ) {
    CouponTask currentTask = couponTaskService.selectById(taskId);
    if (currentTask == null) {
      return ResponseData.build(
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
          "卡券不存在"
      );
    }
    if (!CouponTaskStatus.contains(status)) {
      return ResponseData.build(
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
          "状态不存在"
      );
    }

    // 未启用 --> 启用
    if (currentTask.getStatusId().equals(CouponTaskStatus.NOT_ENABLE.getValue()) &&
        status.equals(CouponTaskStatus.ENABLE.getValue())) {

      try {
        couponTaskService.startProcess(currentTask);
      } catch (Exception e) {
        return ResponseData.build(
            ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
            "启动卡券 批量创建优惠券失败"
        );
      }
    }

    // 启用 --> 禁用
    if (currentTask.getStatusId().equals(CouponTaskStatus.ENABLE.getValue()) &&
        status.equals(CouponTaskStatus.FORBIDDEN.getValue())
        ) {
      couponTaskService.updateStatus(
          currentTask.getId(),
          CouponTaskStatus.ENABLE.getValue(),
          CouponTaskStatus.FORBIDDEN.getValue()
      );
    }
    // 禁用 --> 启用
    if (currentTask.getStatusId().equals(CouponTaskStatus.FORBIDDEN.getValue()) &&
        status.equals(CouponTaskStatus.ENABLE.getValue())
        ) {

      couponTaskService.updateStatus(
          currentTask.getId(),
          CouponTaskStatus.FORBIDDEN.getValue(),
          CouponTaskStatus.ENABLE.getValue()
      );
    }

    return ResponseData.build(
        ResponseBackCode.SUCCESS.getValue(),
        "成功"
    );
  }

  @ApiOperation(nickname = "increaseCouponStockQty", value = "增加卡券库存")
  @PutMapping("/increase-stock/{taskId}")
  public ResponseData increaseStockQty(
      @PathVariable(value = "taskId") Long taskId,
      @RequestParam(value = "qty") Integer qty
  ) {
    CouponTask currentTask = couponTaskService.selectById(taskId);
    if (currentTask == null) {
      return ResponseData.build(
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
          "卡券不存在"
      );
    }

    if (currentTask.getStatusId().equals(CouponTaskStatus.FORBIDDEN.getValue())) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          "卡券已被禁用，不得修改数量"
      );
    }

    if (qty.compareTo(currentTask.getStockQty()) <= 0) {
      return ResponseData.build(
          ResponseBackCode.ERROR_UPDATE_FAIL.getValue(),
          "修改卡券数量必须比原卡券数量大"
      );
    }

    try {
      couponTaskService.increaseStock(currentTask, qty);
    } catch (Exception e) {
      return ResponseData.build(
          ResponseBackCode.ERROR_UPDATE_FAIL.getValue(),
          e.getMessage()
      );
    }

    return ResponseData.build(
        ResponseBackCode.SUCCESS.getValue(),
        "成功"
    );
  }

  @ApiOperation(nickname = "deleteCouponTask", value = "删除卡券")
  @PutMapping("/delete/{taskId}")
  public ResponseData destroy(@PathVariable(value = "taskId") Long taskId) {
    CouponTask task = couponTaskService.selectById(taskId);
    if (task == null) {
      return ResponseData.build(
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
          "卡券不存在"
      );
    }
    if (!CouponTaskStatus.NOT_ENABLE.getValue().equals(task.getStatusId())) {
      // 如果不在 "未启用状态" ，不可编辑
      return ResponseData.build(
          ResponseBackCode.ERROR_UPDATE_FAIL.getValue(),
          "卡券只有在未启用状态才能被删除"
      );
    }

    couponTaskService.deleteById(taskId);
    return ResponseData.build(
        ResponseBackCode.SUCCESS.getValue(),
        "删除成功"
    );
  }

  @LogAnnotation
  @PostMapping("/getCouponTaskList")
  public ResponseData<PageResponse<GetCouponTaskListResponse>> getCouponTaskList(@RequestBody GetCouponTaskListRequest requestParam) {
    return couponTaskService.getCouponTaskList(requestParam);
  }

  @LogAnnotation
  @GetMapping("/getCouponTask")
  public ResponseData getCouponTask(@RequestParam Long id) {
    return couponTaskService.getCouponTask(id);
  }

  @LogAnnotation
  @GetMapping("/getCouponTaskByCardId")
  public ResponseData getCouponTaskByCardId(@RequestParam String cardId) {
    return couponTaskService.getCouponTaskByCardId(cardId);
  }

}

