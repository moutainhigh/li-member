/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.aspect;

import com.siyueli.platform.member.constants.MemberConstants;
import com.siyueli.platform.service.member.server.service.member.MemberUserLoginTokenService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.exceptions.exception.PermissionDenyException;

/**
 * 权限拦截器,验证token
 */
@Aspect
@Order(100)
@Component
public class PermissionAspect {

  @Autowired
  private MemberUserLoginTokenService memberUserLoginTokenService;

  @Pointcut("@annotation(com.siyueli.platform.service.member.server.annotation.PermissionAnnotation)")
  private void permission() {
  }

  @Around("permission()")
  public Object judgetPermission(ProceedingJoinPoint point) throws Throwable {
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      String token = request.getHeader("token");
      if (token == null || "".equals(token)) {
        throw new PermissionDenyException(
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
        );
      }
      //验证token
      ResponseData responseData = memberUserLoginTokenService.checkToken(token);
      if (!responseData.getCode().equals(ResponseBackCode.SUCCESS.getValue())) {
        throw new PermissionDenyException(
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
        );
      }
      request.setAttribute(MemberConstants.USER_ID,responseData.getData());
      return point.proceed(point.getArgs());
  }
}
