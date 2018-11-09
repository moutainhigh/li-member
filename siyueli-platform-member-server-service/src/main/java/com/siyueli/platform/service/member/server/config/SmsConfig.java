/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Primary
@ConfigurationProperties(prefix = "sms")
@Data
public class SmsConfig {

  /**
   * 发送短信的的url
   */
  private String uri_send_sms;

  /**
   * 短信 apikey
   */
  private String apikey;

}
