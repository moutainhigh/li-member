/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.service.region;

import com.siyueli.platform.service.member.client.callback.region.RegionServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员管理服务生产者的接口
 */
@FeignClient(name = "siyueli-member-service", fallback = RegionServiceFallBack.class)
public interface RegionService {

  @RequestMapping(value = "/member/region/index", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData index();


}
