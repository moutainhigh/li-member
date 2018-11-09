/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.config.wechat;

import com.siyueli.platform.service.member.server.config.wechat.handler.LogHandler;
import com.siyueli.platform.service.member.server.config.wechat.handler.MsgHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import redis.clients.jedis.JedisCluster;

@Configuration
@ConditionalOnClass(value = { WxMpService.class, JedisCluster.class })
@ConfigurationProperties(prefix = "wechat.mp")
@Data
public class WechatMpConfig {


  /**
   * 设置微信公众号的appid
   */
  private String appId;

  /**
   * 设置微信公众号的app secret
   */
  private String secret;

  /**
   * 设置微信公众号的token
   */
  private String token;

  /**
   * 设置微信公众号的EncodingAESKey
   */
  private String aesKey;



  @Autowired
  protected LogHandler logHandler;

  @Autowired
  private MsgHandler msgHandler;

  @Autowired
  private JedisCluster jedisCluster;

  @Bean
  @ConditionalOnMissingBean
  public WxMpConfigStorage configStorage() {
    System.out.println("微信APPID：" + this.getAppId() + ",微信Secret:" + this.getSecret());
    WxMpOnRedisConfigStorage configStorage = new WxMpOnRedisConfigStorage(jedisCluster);
    configStorage.setAppId(this.getAppId());
    configStorage.setSecret(this.getSecret());
    configStorage.setToken(this.getToken());
    configStorage.setAesKey(this.getAesKey());
    return configStorage;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxMpService wxMpService(WxMpConfigStorage configStorage) {
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.okhttp.WxMpServiceImpl();
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.jodd.WxMpServiceImpl();
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.apache.WxMpServiceImpl();
    WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(configStorage);
    return wxMpService;
  }

  @Bean
  public WxMpMessageRouter router(WxMpService wxMpService) {
    final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

    // 记录所有事件的日志 （异步执行）
    newRouter.rule().handler(this.logHandler).next();

    // 默认
    newRouter.rule().async(false).handler(this.getMsgHandler()).end();

    return newRouter;

  }

  protected MsgHandler getMsgHandler() {
    return this.msgHandler;
  }
}
