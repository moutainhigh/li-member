package com.siyueli.platform.service.member.server.controller.paymentcode;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.request.paymentcode.deductmoney.DeductMoneyRequest;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.service.paymentcode.PaymentCodeServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 付款码
 */
@RestController
@RequestMapping("/paymentCode")
public class PaymentCodeController {

    @Autowired
    private PaymentCodeServiceContract paymentCodeServiceContract;

    @LogAnnotation
    @PostMapping("/getPaymentCode")
    public ResponseData getPaymentCode() {
        try {
            return paymentCodeServiceContract.getPaymentCode();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        }
    }

    @LogAnnotation
    @PostMapping("/deductMoney")
    public ResponseData deductMoney(@RequestBody DeductMoneyRequest deductMoneyRequest) {
        try {
            return paymentCodeServiceContract.deductMoney(deductMoneyRequest.getPaymentCode(), deductMoneyRequest.getOrderNo());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        }
    }
}
