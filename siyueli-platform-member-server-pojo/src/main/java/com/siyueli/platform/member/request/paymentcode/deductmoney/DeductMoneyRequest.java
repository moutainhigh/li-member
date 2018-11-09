package com.siyueli.platform.member.request.paymentcode.deductmoney;

import lombok.Data;

@Data
public class DeductMoneyRequest {
    private String paymentCode;
    private String orderNo;
}
