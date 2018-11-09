/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.util.IpUtils;
import cn.siyue.platform.util.JsonUtils;

@Aspect
@Order(5)
@Component
public class LoggerAspect {

  StringBuffer builder = new StringBuffer();

  private final static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

  @SuppressWarnings("unchecked")
  @Pointcut("@annotation(com.siyueli.platform.service.member.server.annotation.LoggerAspectAnnotation))")
  public void log() {

  }

  @Before("log()")
  public void doBefore(JoinPoint joinPoint) {

    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    builder.append("{").append("\"address\":" + "\"" + request.getRequestURL().toString() + "\",")
        .append("\"ip\":" + "\"" + IpUtils.getIpAddr(request) + "\",")
        .append("\"classMethod\":" + "\"" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName() + "\",")
        .append("\"httpMethod\":" + "\"" + request.getMethod() + "\",").
        append("\"args\":" + "\"" + Arrays.toString(joinPoint.getArgs()) + "\",");
  }

  @Around("log()")
  public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    try {
      long startTime = System.currentTimeMillis();
      Object ob = proceedingJoinPoint.proceed();
      builder.append("\"time\":" + "\"" + (System.currentTimeMillis() - startTime) + "\",");
      return ob;
    } catch (Exception e) {
      logger.error("error:", e);
      return ResponseData.build(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }

  @AfterReturning(pointcut = "log()", returning = "object")//打印输出结果
  public void doAfterReturing(Object object) {
    builder.append("\"results\":" + JsonUtils.objectToJson(object)).append("}");
    logger.info(builder.toString());
  }
}
