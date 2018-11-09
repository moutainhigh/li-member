/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.config.wechat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;

public abstract class AbstractHandler implements WxMpMessageHandler {
  protected Logger logger = LoggerFactory.getLogger(getClass());
}
