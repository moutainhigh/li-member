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
@ConfigurationProperties(prefix = "weixin.miniprogram")
@Data
public class WeixinConfig {

  /**
   * 小程序唯一标识
   */
  private String appid;

  /**
   * 小程序的 app secret
   */
  private String secret;

  /**
   * 填写为 authorization_code
   */
  private String grant_type;

  /**
   * 小程序登录校验url
   */
  private String url;

  /**
   * 微信支付商户号
   */
  private String mch_id;

  private String recharge_notify_url;

  /**
   * API密钥
   */
  private String key;

}
