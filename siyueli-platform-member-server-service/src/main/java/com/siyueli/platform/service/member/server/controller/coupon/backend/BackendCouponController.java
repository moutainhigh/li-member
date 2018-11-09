package com.siyueli.platform.service.member.server.controller.coupon.backend;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.pojo.coupon.Coupon;
import com.siyueli.platform.member.request.coupon.AddCouponRequest;
import com.siyueli.platform.member.request.coupon.GetCouponListRequest;
import com.siyueli.platform.member.request.coupon.GetCouponRequest;
import com.siyueli.platform.member.request.coupon.UpdateCouponRequest;
import com.siyueli.platform.service.member.server.service.coupon.CouponServiceContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/backend/coupon")
public class BackendCouponController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackendCouponController.class);

    @Autowired
    private CouponServiceContract couponServiceContract;

    @LogAnnotation
    @PostMapping("/addCoupon")
    public ResponseData addCoupon(@RequestBody AddCouponRequest requestParam) {
        try {
            return couponServiceContract.add(requestParam);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseUtil.fail();
        }
    }

    @LogAnnotation
    @PostMapping("/updateCoupon")
    public ResponseData updateCoupon(@RequestBody UpdateCouponRequest requestParam) {
        try {
            return couponServiceContract.update(requestParam);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseUtil.fail();
        }
    }

    @LogAnnotation
    @PostMapping("/getCoupon")
    public ResponseData getCoupon(@RequestBody GetCouponRequest requestParam) {
        try {
            return couponServiceContract.getCoupon(requestParam);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseUtil.fail();
        }
    }

    @LogAnnotation
    @PostMapping("/getCouponList")
    public ResponseData getCouponList(@RequestBody GetCouponListRequest requestParam) {
        try {
            return couponServiceContract.getCouponList(requestParam);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseUtil.fail();
        }
    }

    @LogAnnotation
    @PostMapping("/updateCouponStatus")
    public ResponseData updateCouponStatus(@RequestParam Long id, @RequestParam Integer status) {
        try {
            Coupon coupon = couponServiceContract.selectById(id);
            if (coupon != null) {
                coupon.setStatusId(status);
                coupon.setUpdatedAt(LocalDateTime.now());
                couponServiceContract.updateAllColumnById(coupon);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);

        }
        return ResponseUtil.fail();
    }
}
