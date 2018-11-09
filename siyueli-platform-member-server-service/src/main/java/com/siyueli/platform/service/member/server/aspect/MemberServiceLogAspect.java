package com.siyueli.platform.service.member.server.aspect;

import cn.siyue.platform.httplog.aspect.LogAspect;
import com.siyueli.platform.service.member.server.util.UserLoginUtil;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemberServiceLogAspect extends LogAspect {
    @Autowired
    private UserLoginUtil userLoginUtil;

    @Override
    public Long getUserId() {
        return userLoginUtil.getUserId();
    }

    @Override
    public String getSystemName() {
        return "Member service";
    }
}
