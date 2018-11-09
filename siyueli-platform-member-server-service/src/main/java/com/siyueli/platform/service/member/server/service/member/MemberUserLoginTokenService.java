/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.service.member;

import cn.siyue.platform.base.ResponseData;

/**
 * 用户登录、token验证
 */
public interface MemberUserLoginTokenService {

  /**
   * token验证 用redis保存、验证
   *
   * @param token 传入验证token的值
   */
  ResponseData checkToken(String token);

  /**
   * 根据token，获取用户信息
   */
  ResponseData getUserByToken(String token);

  /**
   * 存储验证码
   *
   * @param cellPhone     手机号
   * @param identityCode 验证码
   */
  ResponseData storeCode(String cellPhone, String identityCode);

  ResponseData checkBackendToken(String token);

  public String getUserIdByBackendToken(String token);

}
