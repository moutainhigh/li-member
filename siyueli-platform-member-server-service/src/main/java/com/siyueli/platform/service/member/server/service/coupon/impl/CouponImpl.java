package com.siyueli.platform.service.member.server.service.coupon.impl;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.coupon.Coupon;
import com.siyueli.platform.member.pojo.coupon.CouponTask;
import com.siyueli.platform.member.request.coupon.AddCouponRequest;
import com.siyueli.platform.member.request.coupon.GetCouponListRequest;
import com.siyueli.platform.member.request.coupon.GetCouponRequest;
import com.siyueli.platform.member.request.coupon.UpdateCouponRequest;
import com.siyueli.platform.member.response.coupon.CouponResponse;
import com.siyueli.platform.service.member.server.mapper.coupon.CouponMapper;
import com.siyueli.platform.service.member.server.service.coupon.CouponServiceContract;

import com.siyueli.platform.service.member.server.util.ConvertUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券 服务实现类
 * </p>
 */
@Service
public class CouponImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponServiceContract {

  /**
   * 由26个小写字母+26个大写字母+0到9个数字构成的字符串，然后打乱顺序得到。
   */
  final static String rawString = "abcdOPQRSTmnop01234qrstuMNUVWvwxyzABGHIefghi567jklJKCDEFLXYZ89";

  @Override
  public ResponseData add(AddCouponRequest requestParam) {
    Coupon coupon = new Coupon();
    BeanUtils.copyProperties(requestParam, coupon);

    LocalDateTime now = LocalDateTime.now();
    coupon.setUpdatedAt(now);
    coupon.setCreatedAt(now);
    insertAllColumn(coupon);

    return ResponseUtil.success();
  }

  @Override
  public ResponseData update(UpdateCouponRequest requestParam) {
    Coupon coupon = selectById(requestParam.getId());
    if (coupon != null) {
      LocalDateTime now = LocalDateTime.now();
      coupon.setUpdatedAt(now);
      updateAllColumnById(coupon);
      return ResponseUtil.success();
    }

    return ResponseUtil.fail();
  }

  @Override
  public ResponseData<CouponResponse> getCoupon(GetCouponRequest requestParam) {
    Coupon coupon = null;
    if (requestParam.getId() != null) {
      coupon = selectById(requestParam.getId());
    } else if (StringUtils.isNotEmpty(requestParam.getCode())) {
      EntityWrapper<Coupon> entityWrapper = new EntityWrapper<Coupon>();
      coupon = selectOne(entityWrapper);
    }
    if (coupon != null) {
      CouponResponse couponVo = new CouponResponse();
      BeanUtils.copyProperties(coupon, couponVo);
      return ResponseUtil.success(couponVo);
    }
    return ResponseUtil.fail();
  }

  @Override
  public ResponseData getCouponList(GetCouponListRequest requestParam) {
    EntityWrapper<Coupon> entityWrapper = new EntityWrapper<Coupon>();
    Page<Coupon> page = new Page<Coupon>(requestParam.getPage(), requestParam.getSize());
    Page<Coupon> pageResult = selectPage(page, entityWrapper);
    try {
      PageResponse<CouponResponse> pageResponse = ConvertUtil.getPageResponse(pageResult, CouponResponse.class);
      return ResponseUtil.success(pageResponse);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseUtil.fail();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean batchCoupon(CouponTask task, int generateNumber) {

    while (generateNumber > 0) {
      generateNumber--;
      Coupon coupon = new Coupon();
      // 生成code码的字符串长度
      int codeNumber = 6;
      // 生成code
      String code = generateCode(codeNumber);
      int count = this.selectCount(new EntityWrapper<>(new Coupon(code)));
      while (count > 0) {
        code = generateCode(codeNumber);
        count = this.selectCount(new EntityWrapper<>(new Coupon(code)));
      }
      coupon.setCode(code);
      coupon.setTaskId(task.getId());
      coupon.setTaskTypeId(task.getTypeId());
      if (task.getValidTimeStartAt() != null && task.getValidTimeEndAt() != null) {
        coupon.setValidTimeStartAt(task.getValidTimeStartAt());
        coupon.setValidTimeEndAt(task.getValidTimeEndAt());
      }
      baseMapper.insert(coupon);
    }

    return true;
  }

  /**
   * 生成优惠券code
   */
  private String generateCode(Integer number) {
    /**
     *  生成规则：取到当前日期是一年中第几天 记为dayOfYear，然后对dayOfYear进行62取模
     *  为什么是62，因为rawString是由26+26+10个字符组成。
     *  取模得到的数position拿到rawString中position位置上的字符，该字符作为code的第一位
     *  目的是为了减少循环检查code唯一性。
     */
    //得到当前日期是在这年的第几天
    Integer dayOfYear = LocalDate.now().getDayOfYear();
    Integer position = dayOfYear % 62;
    char prefix = rawString.charAt(position);
    //生成指定长度的字母和数字的随机组合字符串
    String postfixCode = RandomStringUtils.randomAlphanumeric(number - 1);

    return new StringBuilder().append(prefix).append(postfixCode).toString();
  }
}
