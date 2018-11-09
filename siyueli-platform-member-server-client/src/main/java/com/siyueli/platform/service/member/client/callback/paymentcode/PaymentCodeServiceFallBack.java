package com.siyueli.platform.service.member.client.callback.paymentcode;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.request.paymentcode.deductmoney.DeductMoneyRequest;
import com.siyueli.platform.service.member.client.callback.BaseServiceFallBack;
import com.siyueli.platform.service.member.client.service.paymentcode.PaymentCodeService;
import org.springframework.stereotype.Component;

@Component
public class PaymentCodeServiceFallBack extends BaseServiceFallBack implements PaymentCodeService {

    @Override
    public ResponseData getPaymentCode() {
        return getDownGradeResponse();
    }

    @Override
    public ResponseData deductMoney(DeductMoneyRequest deductMoneyRequest) {
        return getDownGradeResponse();
    }
}
