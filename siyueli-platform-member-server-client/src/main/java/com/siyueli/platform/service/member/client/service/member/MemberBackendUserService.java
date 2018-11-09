/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.service.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.member.backendUserLogin.BackendUserLoginRequest;
import com.siyueli.platform.member.request.member.searchAllUser.SearchAllUserRequest;
import com.siyueli.platform.member.request.member.updateAmount.UpdateBalanceRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateBackendMemberUserRequest;
import com.siyueli.platform.service.member.client.callback.member.MemberBackendUserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员后台管理服务生产者的接口
 */
@FeignClient(name = "siyueli-member-service", path = "/member/backend/user", fallback = MemberBackendUserServiceFallBack.class)
public interface MemberBackendUserService {


  @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateUser(@PathVariable(value = "userId") Long userId,UpdateBackendMemberUserRequest request);

  @RequestMapping(value = "/deleteAllUser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData deleteAllUser(List<Long> userIds);

  @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData deleteUser(@PathVariable(value = "userId") Long userId);

  @RequestMapping(value = "/searchAllUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  ResponseData<Page> searchAllUser(@RequestParam("page") int page, @RequestParam("size") int size,SearchAllUserRequest request);

  @RequestMapping(value = "/searchUser/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData searchUser(@PathVariable(value = "userId") Long userId);

  @RequestMapping(value = "/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData token();

  @RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData userLogin(BackendUserLoginRequest backendUserLoginRequest);

  @RequestMapping(value = "/updateBalance/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateBalance(@PathVariable(value = "userId") Long userId,UpdateBalanceRequest request);

  @RequestMapping(value = "/updatePoints/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updatePoints(@PathVariable(value = "userId") Long userId,UpdateBalanceRequest request);

  @RequestMapping(value = "/searchBalance/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page> searchBalance(@PathVariable(value = "userId") Long userId, @RequestParam("page") int page, @RequestParam("size") int size);

  @RequestMapping(value = "/searchPoints/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page> searchPoints(@PathVariable(value = "userId") Long userId, @RequestParam("page") int page, @RequestParam("size") int size);

}
