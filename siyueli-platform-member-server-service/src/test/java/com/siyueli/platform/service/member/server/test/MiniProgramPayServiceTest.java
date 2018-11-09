package com.siyueli.platform.service.member.server.test;

import com.siyueli.platform.member.vo.payment.OrderQueryVo;
import com.siyueli.platform.service.member.server.service.payment.impl.MiniProgramPayServiceImpl;
import org.junit.Test;

public class MiniProgramPayServiceTest {

    @Test
    public void testOrderQuery() {
        MiniProgramPayServiceImpl miniProgramPayService = new MiniProgramPayServiceImpl();

        String appid = System.getenv("weixin.appid");
        String mch_id = System.getenv("weixin.mch_id");
        String key = System.getenv("weixin.key");
        String out_trade_no = "R1531730107353210934";

        OrderQueryVo oq = new OrderQueryVo();
        oq.setAppid(appid);
        oq.setMch_id(mch_id);
        oq.setOut_trade_no(out_trade_no);
        miniProgramPayService.orderQuery(oq, key);
    }
}
