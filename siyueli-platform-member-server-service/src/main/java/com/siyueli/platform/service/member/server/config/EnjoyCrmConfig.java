package com.siyueli.platform.service.member.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ConfigurationProperties(prefix = "enjoycrm")
@Data
public class EnjoyCrmConfig {

    private String service_url;

    private String userno;

    private String key;
}
