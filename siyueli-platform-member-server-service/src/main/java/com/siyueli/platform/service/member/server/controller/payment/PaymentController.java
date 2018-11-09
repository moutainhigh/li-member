package com.siyueli.platform.service.member.server.controller.payment;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.payment.GetAccountLogRequest;
import com.siyueli.platform.member.request.payment.RechargeRequest;
import com.siyueli.platform.member.response.payment.GetAccountLogResponse;
import com.siyueli.platform.member.response.payment.GetRechargeResultResponse;
import com.siyueli.platform.member.response.payment.RechargeResponse;
import com.siyueli.platform.member.vo.payment.NotifyResultResponse;
import com.siyueli.platform.member.vo.payment.NotifyResultVo;
import com.siyueli.platform.service.member.server.annotation.LoggerAspectAnnotation;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.config.WeixinConfig;
import com.siyueli.platform.service.member.server.service.payment.PaymentService;
import com.siyueli.platform.service.member.server.service.payment.impl.PaymentServiceImpl;
import com.siyueli.platform.service.member.server.util.XStreamUtil;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);


    @Autowired
    private PaymentService paymentService;

    @Autowired
    private WeixinConfig weixinConfig;

    /**
     * 充值
     * @param rechargeRequest
     * @return
     */
    @LogAnnotation
    @PostMapping("/recharge")
    public ResponseData<RechargeResponse> recharge(@RequestBody RechargeRequest rechargeRequest) {
        try {
            return paymentService.recharge(rechargeRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

    /**
     * 充值结果通知
     * @param content
     * @return
     */
    @LogAnnotation
    //@LoggerAspectAnnotation
    @PostMapping("/rechargeNotify")
    public String rechargeNotify(@RequestBody String content) {
        try {
            LOGGER.info("rechargeNotify content: " + content);
            NotifyResultVo notifyResult = paymentService.getNotifyResult(content);
            return paymentService.handleNotifyResult(notifyResult);
        } catch (Exception e) {
            e.printStackTrace();
            return getResponse("FAIL", "处理异常");
        }
    }

    /**
     * 得到充值结果
     * @param orderNo
     * @return
     */
    @LogAnnotation
    @GetMapping("/getRechargeResult")
    public ResponseData<GetRechargeResultResponse> getRechargeResult(@RequestParam("orderNo") String orderNo) {
        try {
            return paymentService.getRechargeResult(orderNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

    /**
     * 得到账户变更记录
     * @param getAccountLogRequest
     * @return
     */
    @LogAnnotation
    @PostMapping("/getAccountLog")
    public ResponseData<PageResponse<GetAccountLogResponse>> getAccountLog(@Valid @RequestBody GetAccountLogRequest getAccountLogRequest) {
        try {
            return paymentService.getAccountLog(getAccountLogRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.build(
                    ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
            );
        }
    }

    private String getResponse(String return_code, String return_msg) {
        NotifyResultResponse resonse = new NotifyResultResponse();
        resonse.setReturn_code(return_code);
        resonse.setReturn_msg(return_msg);
        XStream xstream = XStreamUtil.getXStream(NotifyResultResponse.class);
        String xml = xstream.toXML(resonse);
        return xml;
    }


}
