package com.siyueli.platform.service.member.client.controller.coupon;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.coupon.CouponTaskListRequest;
import com.siyueli.platform.member.request.coupon.CouponTaskRequest;
import com.siyueli.platform.member.response.coupon.CouponTaskDetailsResponse;
import com.siyueli.platform.member.response.coupon.CouponTaskResponse;
import com.siyueli.platform.service.member.client.service.coupon.CouponTaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 卡券任务 前端控制器
 * </p>
 */
@RestController
@Api(tags = "斯越里_后台_卡券任务")
@RequestMapping(path = "/coupon-task", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CouponTaskController {

  private CouponTaskService couponTaskService;

  @Autowired
  public CouponTaskController(CouponTaskService couponTaskService) {
    this.couponTaskService = couponTaskService;
  }

  @ApiOperation(nickname = "getCouponTasks", value = "获取卡券列表")
  @GetMapping(value = "")
  public ResponseData<Page<CouponTaskResponse>> index(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @Valid CouponTaskListRequest couponTaskListRequest,
      BindingResult result
  ) {
    if (result.hasErrors()) {
      return new ResponseData<Page<CouponTaskResponse>>(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          result.getFieldError().getDefaultMessage()
      );
    }

    try {
      return couponTaskService.index(page, size, couponTaskListRequest);
    } catch (Exception e) {

      return new ResponseData<Page<CouponTaskResponse>>(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }

  @ApiOperation(nickname = "showCouponTask", value = "展示卡券任务")
  @GetMapping("/{taskId}")
  public ResponseData<CouponTaskDetailsResponse> show(
      @PathVariable(value = "taskId") Long taskId,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    try {
      return couponTaskService.show(taskId, page, size);
    } catch (Exception e) {

      return new ResponseData<CouponTaskDetailsResponse>(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }

  @ApiOperation(nickname = "createCouponTask", value = "新建卡券任务")
  @PostMapping("")
  public ResponseData<CouponTaskResponse> store(
      @RequestBody @Valid CouponTaskRequest couponTaskRequest,
      BindingResult errors
  ) {

    // 扩展验证
    errors = couponTaskRequest.appendValidation(errors);

    if (errors.hasErrors()) {

      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          couponTaskRequest.joinErrorMessage(errors)
      );
    }

    try {
      return couponTaskService.store(couponTaskRequest);
    } catch (Exception e) {

      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }

  @ApiOperation(nickname = "updateCouponTask", value = "更新卡券任务")
  @PutMapping("/{taskId}")
  public ResponseData<CouponTaskResponse> update(
      @PathVariable String taskId,
      @RequestBody @Valid CouponTaskRequest couponTaskRequest,
      BindingResult errors
  ) {

    // 扩展验证
    errors = couponTaskRequest.appendValidation(errors);

    if (errors.hasErrors()) {

      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          couponTaskRequest.joinErrorMessage(errors)
      );
    }

    try {
      return couponTaskService.update(taskId, couponTaskRequest);
    } catch (Exception e) {

      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }

  @ApiOperation(nickname = "switchingCouponTask", value = "启用/禁用卡券")
  @PutMapping("/switching/{taskId}")
  public ResponseData switchStatus(
      @PathVariable(value = "taskId") Long taskId,
      @RequestParam(value = "status") Integer status
  ) {
    try {
      return couponTaskService.switchStatus(taskId, status);
    } catch (Exception e) {

      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }

  @ApiOperation(nickname = "increaseCouponStockQty", value = "增加卡券库存")
  @PutMapping("/increase-stock/{taskId}")
  public ResponseData increaseStockQty(
      @PathVariable(value = "taskId") Long taskId,
      @RequestParam(value = "qty") Integer qty
  ) {
    return couponTaskService.increaseStockQty(taskId, qty);
  }

  @ApiOperation(nickname = "deleteCouponTask", value = "删除卡券")
  @PutMapping("/delete/{taskId}")
  public ResponseData destroy(@PathVariable(value = "taskId") Long taskId) {
    try {
      return couponTaskService.destroy(taskId);
    } catch (Exception e) {

      return new ResponseData<CouponTaskResponse>(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }
}

