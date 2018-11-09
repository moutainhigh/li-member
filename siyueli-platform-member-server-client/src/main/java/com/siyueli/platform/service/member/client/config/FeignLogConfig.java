/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import feign.Logger;
import feign.Logger.Level;

@Configuration
@Profile("develop") // 只有开发环境develop才能打印Feign日志
public class FeignLogConfig {

  @Bean Logger.Level feignLoggerLevel() {
    return Level.FULL;
  }

}
