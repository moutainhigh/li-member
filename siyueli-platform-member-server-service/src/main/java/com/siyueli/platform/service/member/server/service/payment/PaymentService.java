package com.siyueli.platform.service.member.server.service.payment;

import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.payment.GetAccountLogRequest;
import com.siyueli.platform.member.request.payment.RechargeRequest;
import com.siyueli.platform.member.response.payment.GetAccountLogResponse;
import com.siyueli.platform.member.response.payment.GetRechargeResultResponse;
import com.siyueli.platform.member.response.payment.RechargeResponse;
import com.siyueli.platform.member.vo.payment.NotifyResultVo;

public interface PaymentService {

    public ResponseData<RechargeResponse> recharge(RechargeRequest rechargeRequest);

    public NotifyResultVo getNotifyResult(String content);

    public String handleNotifyResult(NotifyResultVo notifyResult);

    public ResponseData<GetRechargeResultResponse> getRechargeResult(String orderNo);

    public ResponseData<PageResponse<GetAccountLogResponse>> getAccountLog(GetAccountLogRequest getAccountLogRequest);
}
