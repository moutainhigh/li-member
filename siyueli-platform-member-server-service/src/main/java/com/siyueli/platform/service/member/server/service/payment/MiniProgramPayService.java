package com.siyueli.platform.service.member.server.service.payment;

import com.siyueli.platform.member.vo.payment.OrderQueryResultVo;
import com.siyueli.platform.member.vo.payment.OrderQueryVo;

public interface MiniProgramPayService {


    public OrderQueryResultVo orderQuery(OrderQueryVo oq);

    public OrderQueryResultVo orderQuery(String out_trade_no);

    public OrderQueryResultVo orderQuery(OrderQueryVo oq, String key);
}
