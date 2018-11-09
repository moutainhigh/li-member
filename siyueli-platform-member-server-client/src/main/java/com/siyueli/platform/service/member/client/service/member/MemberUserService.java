/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.service.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.member.request.member.registerUser.RegisterUserRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateMemberUserRequest;
import com.siyueli.platform.member.request.member.userLogin.UserLoginRequest;
import com.siyueli.platform.member.response.member.userLogin.UserLoginResponse;
import com.siyueli.platform.service.member.client.callback.member.MemberUserServiceFallBack;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员前台服务生产者的接口
 */
@FeignClient(name = "siyueli-member-service", path = "/member/user", fallback = MemberUserServiceFallBack.class)
public interface MemberUserService {

  @RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData registerUser(RegisterUserRequest request);

  @RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData userLogin(UserLoginRequest request);

  @RequestMapping(value = "/userLogout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData userLogout();

  @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData getUserInfo();

  @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateUser(@PathVariable(value = "userId") Long userId,UpdateMemberUserRequest request);


  @RequestMapping(value = "/searchAllGrade", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page> searchAllGrade(@RequestParam("page") int page, @RequestParam("size") int size);


}
