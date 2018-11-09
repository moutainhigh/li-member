package com.siyueli.platform.service.member.client.controller.payment;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.payment.GetAccountLogRequest;
import com.siyueli.platform.member.request.payment.RechargeRequest;
import com.siyueli.platform.member.response.payment.GetAccountLogResponse;
import com.siyueli.platform.member.response.payment.GetRechargeResultResponse;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.payment.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Api(tags = "斯越里_充值接口")
@RestController
@RequestMapping(path = "/payment")
public class PaymentController extends BaseController {

    @Autowired
    private PaymentService paymentService;

    @LogAnnotation
    @ApiOperation(nickname = "recharge",value = "充值")
    @PostMapping("/recharge")
    public ResponseData recharge(@RequestBody RechargeRequest rechargeRequest, BindingResult result) {
        ResponseData errorResponse = getErrorResponse(result);
        if (errorResponse != null)
            return errorResponse;

        return paymentService.recharge(rechargeRequest);
    }

    @LogAnnotation
    @ApiOperation(nickname = "rechargeNotify",value = "充值结果通知")
    @PostMapping("/rechargeNotify")
    public String rechargeNotify(@RequestBody String content, BindingResult result) {
        return paymentService.rechargeNotify(content);
    }

    @LogAnnotation
    @ApiOperation(nickname = "getRechargeResult",value = "得到充值结果")
    @GetMapping("/getRechargeResult")
    public ResponseData<GetRechargeResultResponse> getRechargeResult(String orderNo) {
        return paymentService.getRechargeResult(orderNo);
    }

    @LogAnnotation
    @ApiOperation(nickname = "getAccountLog",value = "得到帐户变更记录")
    @GetMapping(path = "/getAccountLog")
    public ResponseData<PageResponse<GetAccountLogResponse>> getAccountLog(GetAccountLogRequest getAccountLogRequest, BindingResult result) {
        return paymentService.getAccountLog(getAccountLogRequest);
    }


}
