package com.siyueli.platform.service.member.server.service.paymentcode;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.paymentcode.PaymentCode;

/**
 * <p>
 * 付款码表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface PaymentCodeServiceContract extends IService<PaymentCode> {
    ResponseData getPaymentCode();

    ResponseData deductMoney(String paymentCode, String orderNo);
}
