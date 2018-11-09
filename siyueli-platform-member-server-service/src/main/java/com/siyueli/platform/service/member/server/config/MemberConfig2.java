package com.siyueli.platform.service.member.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ConfigurationProperties(prefix = "member")
@Data
public class MemberConfig2 {

    private String api_url;

    /**
     * 充值送积分
     */
    private String recharge_award_points;

    /**
     * aes加密的key
     */
    private String aes_key;

    /**
     * md5加密的key
     */
    private String md5_key;
}
