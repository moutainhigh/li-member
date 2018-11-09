package com.siyueli.platform.service.member.server.test;

import com.siyueli.platform.service.member.server.MemberServerApplication;
import com.siyueli.platform.service.member.server.config.MemberConfig2;
import com.siyueli.platform.service.member.server.util.AESUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//properties = {"application.yml, application-siyueli.yml"},
@SpringBootTest(classes = MemberServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ActiveProfiles("siyueli")
public class AESTest {

    @Autowired
    private MemberConfig2 memberConfig2;

    @Test
    public void testPaymentCode() {
        String content = "47fdd478-67bb-490b-bbeb-484260a6b031";
        String paymentCodeStr = AESUtil.encrypt(content,memberConfig2.getAes_key());
        System.out.println("paymentCodeStr: " + paymentCodeStr);
    }
}
