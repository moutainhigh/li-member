package com.siyueli.platform.service.member.client.service.paymentcode;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.request.paymentcode.deductmoney.DeductMoneyRequest;
import com.siyueli.platform.service.member.client.callback.paymentcode.PaymentCodeServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "siyueli-member-service", fallback = PaymentCodeServiceFallBack.class)
public interface PaymentCodeService {

    @RequestMapping(value = "/paymentCode/getPaymentCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData getPaymentCode();

    @RequestMapping(value = "/paymentCode/deductMoney", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData deductMoney(@RequestBody DeductMoneyRequest deductMoneyRequest);
}
