/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.callback.coupon;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.coupon.CouponTaskListRequest;
import com.siyueli.platform.member.request.coupon.CouponTaskRequest;
import com.siyueli.platform.member.response.coupon.CouponTaskDetailsResponse;
import com.siyueli.platform.member.response.coupon.CouponTaskResponse;
import com.siyueli.platform.service.member.client.service.coupon.CouponTaskService;

import org.springframework.stereotype.Component;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

@Component
public class CouponTaskServiceFallBack implements CouponTaskService {

  @Override public ResponseData<Page<CouponTaskResponse>> index(
      int page, int size, CouponTaskListRequest request
  ) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<CouponTaskDetailsResponse> show(Long taskId, int page, int size) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<CouponTaskResponse> store(CouponTaskRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<CouponTaskResponse> update(String taskId, CouponTaskRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData switchStatus(Long taskId, Integer status) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData increaseStockQty(Long taskId, Integer qty) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData destroy(Long taskId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
