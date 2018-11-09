/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.service.rights;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.rights.addRights.SaveMemberRightsRequest;
import com.siyueli.platform.member.request.rights.updateRights.UpdateMemberRightsRequest;
import com.siyueli.platform.member.response.rights.common.MemberRightsResponse;
import com.siyueli.platform.service.member.client.callback.rights.MemberRightsServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.siyue.platform.base.ResponseData;

/**
 * 调用权益服务生产者的接口
 */
@FeignClient(name = "siyueli-member-service", path = "/member/rights", fallback = MemberRightsServiceFallBack.class)
public interface MemberRightsService {

  @RequestMapping(value = "/addRights", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addRights(SaveMemberRightsRequest memberRights);

  @RequestMapping(value = "/updateRights/{rightId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateRights(@PathVariable(value = "rightId") Long rightId, UpdateMemberRightsRequest memberRights);


  @RequestMapping(value = "/setStatus/{rightId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData setStatus(@PathVariable(value = "rightId")  Long rightId);

  @RequestMapping(value = "/searchAllRights", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page> searchAllRights(@RequestParam("page") int page, @RequestParam("size") int size);


  @RequestMapping(value = "/searchSingleRights/{rightId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<MemberRightsResponse> searchSingleRights(@PathVariable(value = "rightId")  Long rightId);

}
