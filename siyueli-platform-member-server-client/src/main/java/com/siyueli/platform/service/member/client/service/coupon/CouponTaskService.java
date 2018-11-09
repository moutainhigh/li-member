/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.service.coupon;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.coupon.CouponTaskListRequest;
import com.siyueli.platform.member.request.coupon.CouponTaskRequest;
import com.siyueli.platform.member.response.coupon.CouponTaskDetailsResponse;
import com.siyueli.platform.member.response.coupon.CouponTaskResponse;
import com.siyueli.platform.service.member.client.callback.coupon.CouponTaskServiceFallBack;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员管理服务生产者的接口
 */
@FeignClient(name = "siyueli-member-service", path = "/coupontask", fallback = CouponTaskServiceFallBack.class)
public interface CouponTaskService {

  @RequestMapping(value = "/list", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  ResponseData<Page<CouponTaskResponse>> index(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      CouponTaskListRequest request
  );

  @RequestMapping(value = "/{taskId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  ResponseData<CouponTaskDetailsResponse> show(@PathVariable(value = "taskId") Long taskId,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(value = "size", required = false, defaultValue = "15") int size);

  @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<CouponTaskResponse> store(CouponTaskRequest request);

  @RequestMapping(value = "/{taskId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<CouponTaskResponse> update(@PathVariable(value = "taskId") String taskId, CouponTaskRequest request);

  @RequestMapping(value = "/switching/{taskId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData switchStatus(
      @PathVariable(value = "taskId") Long taskId,
      @RequestParam(value = "status") Integer status
  );

  @RequestMapping(value = "/increase-stock/{taskId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData increaseStockQty(
      @PathVariable(value = "taskId") Long taskId,
      @RequestParam(value = "qty") Integer qty);


  @RequestMapping(value = "/delete/{taskId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData destroy(@PathVariable(value = "taskId") Long taskId);
}
