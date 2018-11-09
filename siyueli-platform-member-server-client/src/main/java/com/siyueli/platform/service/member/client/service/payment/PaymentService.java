package com.siyueli.platform.service.member.client.service.payment;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.request.payment.GetAccountLogRequest;
import com.siyueli.platform.member.request.payment.RechargeRequest;
import com.siyueli.platform.service.member.client.callback.payment.PaymentServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "siyueli-member-service", fallback = PaymentServiceFallBack.class)
public interface PaymentService {

    @RequestMapping(value = "/payment/recharge", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData recharge(@RequestBody RechargeRequest rechargeRequest);

    @RequestMapping(value = "/payment/rechargeNotify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    String rechargeNotify(@RequestBody String content);

    @RequestMapping(value = "/payment/getRechargeResult", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData getRechargeResult(@RequestParam("orderNo") String orderNo);

    @RequestMapping(value = "/payment/getAccountLog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData getAccountLog(@RequestBody GetAccountLogRequest getAccountLogRequest);

}
