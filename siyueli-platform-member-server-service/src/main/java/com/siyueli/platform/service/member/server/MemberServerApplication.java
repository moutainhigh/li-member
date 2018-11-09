/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 会员管理服务类入口
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.siyueli.platform.service.member.server.*","cn.siyue.platform.*"})
@MapperScan("com.siyueli.platform.service.member.server.mapper*")
public class MemberServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MemberServerApplication.class);
  }

}
