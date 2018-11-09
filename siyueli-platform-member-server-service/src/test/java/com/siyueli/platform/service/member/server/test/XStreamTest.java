package com.siyueli.platform.service.member.server.test;


import com.siyueli.platform.member.vo.payment.UnifiedOrderResultVo;
import com.siyueli.platform.member.vo.payment.UnifiedOrderVo;
import com.siyueli.platform.service.member.server.service.payment.PaymentService;
import com.siyueli.platform.service.member.server.service.payment.impl.PaymentServiceImpl;
import com.siyueli.platform.service.member.server.util.XStreamUtil;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

public class XStreamTest {

    @Test
    public void testXml() {
        XStream xstream = XStreamUtil.getXStream(UnifiedOrderVo.class);
        UnifiedOrderVo vo = new UnifiedOrderVo();
        vo.setAppid("kkk");
        vo.setDetail("kkkssssss");

        String xml = xstream.toXML(vo);

        System.out.println(xml);

        UnifiedOrderVo obj = (UnifiedOrderVo)xstream.fromXML(xml);
        System.out.println("appid: " + obj.getAppid());
    }

    @Test
    public void testSign() {
        PaymentService paymentService = new PaymentServiceImpl();
        paymentService.recharge(null);
    }

    @Test
    public void testXmlResult() {
        UnifiedOrderResultVo result = new UnifiedOrderResultVo();
        result.setAppid("kkk");
        result.setCode_url("kkksssss");
        result.setResult_code("ksssd999");
        result.setPrepay_id("dsllll");
        XStream xstream = XStreamUtil.getXStream(UnifiedOrderResultVo.class);

        String xml = xstream.toXML(result);
        System.out.println("xml内容：");
        System.out.println(xml);

        UnifiedOrderResultVo myResult = (UnifiedOrderResultVo)xstream.fromXML(xml);

        System.out.println(myResult);
    }

}
