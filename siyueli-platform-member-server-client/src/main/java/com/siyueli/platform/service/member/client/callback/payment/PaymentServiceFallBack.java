package com.siyueli.platform.service.member.client.callback.payment;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import com.siyueli.platform.member.request.payment.GetAccountLogRequest;
import com.siyueli.platform.member.request.payment.RechargeRequest;
import com.siyueli.platform.service.member.client.service.payment.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallBack implements PaymentService {
    @Override
    public ResponseData recharge(RechargeRequest rechargeRequest) {
        return getDownGradeResponse();
    }

    @Override
    public String rechargeNotify(String content) {
        String xml = "<xml><return_code>FAIL</return_code><return_msg>网络异常，请稍后重试</return_msg></xml>";
        return xml;
    }

    @Override
    public ResponseData getRechargeResult(String orderNo) {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData getAccountLog(GetAccountLogRequest getAccountLogRequest) {
        return getDownGradeResponse();
    }

    private ResponseData getDownGradeResponse() {
        return ResponseData.build(
                ResponseBackCode.ERROR_DOWNGRADE.getValue(),
                ResponseBackCode.ERROR_DOWNGRADE.getMessage()
        );
    }

}
