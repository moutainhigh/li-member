/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.controller.member;

import com.siyueli.platform.service.member.server.service.member.MemberUserLoginTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 用户登录、验证Token等安全接口
 */
@RestController
public class MemberLoginTokenController {

  private MemberUserLoginTokenService memberUserLoginTokenService;

  @Autowired
  public MemberLoginTokenController(MemberUserLoginTokenService memberUserLoginTokenService){
    this.memberUserLoginTokenService = memberUserLoginTokenService;
  }

  /**
   * @param userCode 用户名
   * @param password 密码
   * @param userType 用户类型(运维管理人员、商户、客户)
   */
  @PostMapping(value = "/auth/userLogin")
  public ResponseData userLogin(
      @RequestParam("userCode") String userCode,
      @RequestParam("password") String password,
      @RequestParam("userType") String userType
  ) {
    try {
      if (userCode == null || "".equals(userCode)
          || password == null || "".equals(password)
          || userType == null || "".equals(userType)) {
        return ResponseData.build(
            ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
            ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
        );
      }
      return null;//memberUserLoginTokenService.userLogin(userCode,password,userType);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseData.build(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }

  /**
   * 验证token
   */
  @GetMapping(value = "/auth/token")
  public ResponseData token(@RequestParam("token") String token) {
    try {
      if (token == null || "".equals(token)) {
        return ResponseData.build(
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
        );
      }
      return memberUserLoginTokenService.checkToken(token);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseData.build(
          ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
          ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
      );
    }
  }
}
