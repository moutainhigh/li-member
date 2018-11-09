package com.siyueli.platform.service.member.server.service.coupon;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.coupon.CouponTask;
import com.siyueli.platform.member.request.coupon.CouponTaskListRequest;
import com.siyueli.platform.member.request.coupon.CouponTaskRequest;
import com.siyueli.platform.member.request.coupon.GetCouponTaskListRequest;
import com.siyueli.platform.member.response.coupon.CouponTaskResponse;
import com.siyueli.platform.member.response.coupontask.GetCouponTaskListResponse;
import com.siyueli.platform.member.response.coupontask.GetCouponTaskResponse;

/**
 * <p>
 * 卡券任务 服务类
 * </p>
 */
public interface CouponTaskServiceContract extends IService<CouponTask> {

  Page<CouponTaskResponse> selectTaskPage(Page<CouponTaskResponse> couponTaskResponsePage, CouponTaskListRequest request);

  public CouponTask createEntity(CouponTaskRequest request);

  public CouponTask updateEntity(CouponTask task, CouponTaskRequest request);

  public Boolean startProcess(CouponTask task);

  public Boolean updateStatus(Long taskId, Integer currentStatus, Integer updateStatus);

  Boolean increaseStock(CouponTask currentTask, Integer qty);

  ResponseData<PageResponse<GetCouponTaskListResponse>> getCouponTaskList(GetCouponTaskListRequest requestParam);

  ResponseData<GetCouponTaskResponse> getCouponTask(Long id);

  ResponseData<GetCouponTaskResponse> getCouponTaskByCardId(String cardId);
}
