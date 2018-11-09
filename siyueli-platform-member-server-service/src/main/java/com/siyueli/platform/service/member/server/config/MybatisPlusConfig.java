/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor page = new PaginationInterceptor();
    page.setDialectType("mysql");
    return page;
  }
}
