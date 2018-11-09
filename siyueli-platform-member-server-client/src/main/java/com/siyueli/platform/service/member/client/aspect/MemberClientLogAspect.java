package com.siyueli.platform.service.member.client.aspect;

import cn.siyue.platform.httplog.aspect.LogAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemberClientLogAspect extends LogAspect {
    @Override
    public Long getUserId() {
        return null;
    }

    @Override
    public String getSystemName() {
        return "Member client";
    }
}
