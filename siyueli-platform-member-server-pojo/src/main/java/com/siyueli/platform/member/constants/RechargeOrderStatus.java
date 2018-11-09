package com.siyueli.platform.member.constants;

public enum RechargeOrderStatus {


    INIT(1, "初始"),
    PAY_SUCCESS(2, "支付成功"),
    PAY_FAIL(3, "支付失败");

    private int value;
    private String message;

    private RechargeOrderStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
