package com.siyueli.platform.service.member.server.mapper.coupon;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.siyueli.platform.member.pojo.coupon.CouponTask;
import com.siyueli.platform.member.request.coupon.CouponTaskListRequest;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 卡券任务 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface CouponTaskMapper extends BaseMapper<CouponTask> {

  Boolean updateByStatus(@Param("taskId") Long taskId, @Param("currentStatus") Integer currentStatus, @Param("updateStatus") Integer updateStatus);

  List<CouponTask> selectTaskPage(@Param("limit") Integer limit,  @Param("offset") Integer offset,  @Param("request") CouponTaskListRequest request);

  Boolean updateByStockQty(@Param("id") Long id, @Param("currentQty") Integer currentQty, @Param("updateQty") Integer updateQty);
}
