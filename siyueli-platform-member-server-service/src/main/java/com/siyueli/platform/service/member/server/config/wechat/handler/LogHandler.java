/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.config.wechat.handler;


import org.springframework.stereotype.Component;

import java.util.Map;

import cn.siyue.platform.util.JsonUtils;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class LogHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(
      WxMpXmlMessage wxMessage,
      Map<String, Object> context, WxMpService wxMpService,
      WxSessionManager sessionManager
  ) {
    this.logger.info("接收到请求消息，内容：{}", JsonUtils.objectToJson(wxMessage));
    return null;
  }
}