/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 会员管理服务消费的应用
 */
@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.siyueli.platform.service.member.client.*","cn.siyue.platform.*"})
public class MemberClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(MemberClientApplication.class);
  }
}
