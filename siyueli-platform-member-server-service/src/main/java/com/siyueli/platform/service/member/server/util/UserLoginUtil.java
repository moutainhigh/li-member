package com.siyueli.platform.service.member.server.util;

import com.siyueli.platform.member.constants.MemberConstants;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.service.member.server.service.member.JedisClusterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginUtil {

    @Autowired
    private JedisClusterService jedisClusterService;

    public Long getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String userId = null;
        if (StringUtils.isNotEmpty(token)) {
            userId = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token);
        }
        MemberUser user = null;
        if (StringUtils.isNotEmpty(userId)) {
            return new Long(userId);
        }
        return null;
    }
}
