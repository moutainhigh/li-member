package com.siyueli.platform.service.member.server.test;

import com.siyueli.platform.member.vo.payment.UnifiedOrderVo;
import com.siyueli.platform.service.member.server.util.ConvertUtil;
import com.siyueli.platform.service.member.server.util.MD5Maker;
import com.siyueli.platform.service.member.server.util.MapSortUtil;
import org.junit.Test;

import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MemberServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MD5Test {

    @Test
    public void testMd5() {
        UnifiedOrderVo vo = new UnifiedOrderVo();

        String appid = System.getenv("weixin.appid");
        String mch_id = System.getenv("weixin.mch_id");
        String key = System.getenv("weixin.key");

        vo.setAppid(appid);
        vo.setMch_id(mch_id);
        vo.setDevice_info("WEB");
        vo.setNonce_str("uld72Z5LGEG3Wf44xMrz0FG5BvMXdgY8");
        vo.setSign_type("MD5");
        vo.setBody("??");
        vo.setOut_trade_no("R1531710575261114002");
        vo.setFee_type("CNY");
        vo.setTotal_fee("100");
        vo.setNotify_url("https://www.sipin.com/dev/siyueli/api/member/payment/rechargeNotify");
        vo.setTrade_type("JSAPI");
        vo.setOpenid("ovF4f5QPo4ZA0xwvyKBQSB3lQ8gg");

        Map map = null;
        try {
            map = ConvertUtil.objectToMap(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map sortedMap = MapSortUtil.sortMapByKey(map);
        String sign = ConvertUtil.getSignatureParams(sortedMap);
        System.out.println("sign: " + sign);
        sign = sign + "&key=" + key;
        System.out.println("sign2: " + sign);

        sign = MD5Maker.getMD5(sign);
        System.out.println(sign);
    }
}
