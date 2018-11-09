package com.siyueli.platform.service.member.server.service.coupon;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.coupon.Coupon;
import com.siyueli.platform.member.pojo.coupon.CouponTask;
import com.siyueli.platform.member.request.coupon.AddCouponRequest;
import com.siyueli.platform.member.request.coupon.GetCouponListRequest;
import com.siyueli.platform.member.request.coupon.GetCouponRequest;
import com.siyueli.platform.member.request.coupon.UpdateCouponRequest;
import com.siyueli.platform.member.response.coupon.CouponResponse;

/**
 * <p>
 * 优惠券 服务类
 * </p>
 *
 *
 *
 */
public interface CouponServiceContract extends IService<Coupon> {


  ResponseData add(AddCouponRequest requestParam);

  ResponseData update(UpdateCouponRequest requestParam);

  ResponseData<CouponResponse> getCoupon(GetCouponRequest requestParam);

  ResponseData getCouponList(GetCouponListRequest requestParam);

  /**
   *
   * @param task 卡券任务
   * @param generateNumber 生成优惠券数量
   * @return
   */
  Boolean batchCoupon(CouponTask task, int generateNumber);


}
