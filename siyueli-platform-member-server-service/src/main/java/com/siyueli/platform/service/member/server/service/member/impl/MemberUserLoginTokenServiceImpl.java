/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.service.member.impl;

import com.siyueli.platform.member.constants.MemberConstants;
import com.siyueli.platform.service.member.server.service.member.MemberUserLoginTokenService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.exceptions.exception.PermissionDenyException;

/**
 * 登录、验证token实现类
 */
@Service
public class MemberUserLoginTokenServiceImpl implements MemberUserLoginTokenService {

  private JedisClusterServiceImpl jedisClusterService;

  @Autowired
  public MemberUserLoginTokenServiceImpl(JedisClusterServiceImpl jedisClusterService){
    this.jedisClusterService = jedisClusterService;
  }

  /**
   * 验证token的方法 采用redis保存token
   *
   * @param token 传入验证token的值
   */
  @Override
  public ResponseData checkToken(String token) {
    try{
      //根据token从redis中查询用户信息
      String json = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token);

      //判断是否为空
      if (StringUtils.isBlank(json)) {
        return ResponseData.build(
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
        );
      }

      //更新过期时间
      jedisClusterService.expire(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token,  MemberConstants.SSO_SESSION_EXPIRE);

      //验证成功
      return ResponseData.build(
          ResponseBackCode.SUCCESS.getValue(),
          ResponseBackCode.SUCCESS.getMessage(),json
      );
    }catch (Exception e){
      throw new PermissionDenyException(
          ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
          ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage());
    }
  }

  /**
   * 根据token查找用户信息
   */
  @Override
  public ResponseData getUserByToken(String token) {
    String userInfo = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token);

    //设置session的过期时间
    jedisClusterService.expire(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token,  MemberConstants.SSO_SESSION_EXPIRE);

    //返回token
    return ResponseData.ok(userInfo);
  }

  /**
   * 发送验证码
   *
   * @param cellphone     手机号
   * @param identityCode 验证码
   */
  @Override
  public ResponseData storeCode(String cellphone, String identityCode) {


    //保存之前对密码清空
    jedisClusterService.set(cellphone + ":" + identityCode, cellphone);

    //设置session的过期时间
    jedisClusterService.expire(cellphone + ":" + identityCode, MemberConstants.SSO_SESSION_EXPIRE);

    return ResponseData.build( ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  public ResponseData checkBackendToken(String token) {
    try{
      //根据token从redis中查询用户信息
      String json = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY_BACKEND + ":" + token);

      //判断是否为空
      if (StringUtils.isBlank(json)) {
        return ResponseData.build(
                ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
                ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
        );
      }

      //更新过期时间
      jedisClusterService.expire(MemberConstants.REDIS_USER_SESSION_KEY_BACKEND + ":" + token,  MemberConstants.SSO_SESSION_EXPIRE);

      //验证成功
      return ResponseData.build(
              ResponseBackCode.SUCCESS.getValue(),
              ResponseBackCode.SUCCESS.getMessage(),json
      );
    }catch (Exception e){
      throw new PermissionDenyException(
              ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
              ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage());
    }
  }

  @Override
  public String getUserIdByBackendToken(String token) {
    String userInfo = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY_BACKEND + ":" + token);
    return userInfo;
  }
}
