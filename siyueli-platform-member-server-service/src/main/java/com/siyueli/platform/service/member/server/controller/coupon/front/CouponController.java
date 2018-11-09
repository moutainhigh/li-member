package com.siyueli.platform.service.member.server.controller.coupon.front;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.coupon.CouponTaskListRequest;
import com.siyueli.platform.member.response.coupon.CouponTaskResponse;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.util.JsonTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.siyue.platform.base.ResponseData;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 优惠券 前台控制器
 * </p>
 */
@RestController
@RequestMapping(path = "/front/coupon", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CouponController {

  private JsonTokenUtil jsonTokenUtil;

  @Autowired
  public CouponController(JsonTokenUtil jsonTokenUtil) {
    this.jsonTokenUtil = jsonTokenUtil;
  }

  @ApiOperation(nickname = "getCouponTasks", value = "用户展示卡券列表")
  @GetMapping(value = "/list")
  public ResponseData<Page<CouponTaskResponse>> index(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @RequestParam("status") Integer statusId
  ) {

    Long userId = jsonTokenUtil.getCurrentUserId();


    // 先搁浅 之后再做
    return null;
  }
}

