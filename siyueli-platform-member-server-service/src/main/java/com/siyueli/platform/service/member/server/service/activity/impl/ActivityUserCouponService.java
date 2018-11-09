package com.siyueli.platform.service.member.server.service.activity.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.activity.ActivityUserCoupon;
import com.siyueli.platform.service.member.server.mapper.activity.ActivityUserCouponMapper;
import com.siyueli.platform.service.member.server.service.activity.ActivityUserCouponServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 规则卡券用户表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class ActivityUserCouponService extends ServiceImpl<ActivityUserCouponMapper, ActivityUserCoupon> implements ActivityUserCouponServiceContract {

}
