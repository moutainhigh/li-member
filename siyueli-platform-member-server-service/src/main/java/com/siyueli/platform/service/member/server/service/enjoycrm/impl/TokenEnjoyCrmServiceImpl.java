package com.siyueli.platform.service.member.server.service.enjoycrm.impl;

import com.siyueli.platform.member.vo.enjoycrm.gettoken.GetTokenRequest;
import com.siyueli.platform.member.vo.enjoycrm.gettoken.GetTokenResponse;
import com.siyueli.platform.service.member.server.config.EnjoyCrmConfig;
import com.siyueli.platform.service.member.server.service.enjoycrm.TokenEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.member.JedisClusterService;
import com.siyueli.platform.service.member.server.util.DesUtil;
import com.siyueli.platform.service.member.server.util.HttpUtils;
import com.siyueli.platform.service.member.server.util.JsonUtil;
import com.siyueli.platform.service.member.server.util.RandomStringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class TokenEnjoyCrmServiceImpl implements TokenEnjoyCrmService {

    @Autowired
    private EnjoyCrmConfig enjoyCrmConfig;

    @Autowired
    private JedisClusterService jedisClusterService;

    private static final String REDIS_KEY_ENJOY_CRM_TOKEN = "enjoycrm:token";

    /**
     * 获取令牌
     * @return
     */
    @Override
    public GetTokenResponse getToken(boolean expiredFlag) {
        try {
            String tokenStr = null;
            GetTokenResponse resp = null;
            if (!expiredFlag) {
                tokenStr = jedisClusterService.get(REDIS_KEY_ENJOY_CRM_TOKEN);

                if (StringUtils.isNotEmpty(tokenStr)) {
                    resp = JsonUtil.jsonToObject(tokenStr, GetTokenResponse.class);
                    return resp;
                }
            }

            resp = getTokenFromEnjoyCrm();
            if (resp != null && !resp.getHasException() && StringUtils.isNotEmpty(resp.getObjectData())) {
                tokenStr = JsonUtil.toJsonString(resp);
                jedisClusterService.set(REDIS_KEY_ENJOY_CRM_TOKEN, tokenStr);
                jedisClusterService.expire(REDIS_KEY_ENJOY_CRM_TOKEN, 30 * 60);
            }
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private GetTokenResponse getTokenFromEnjoyCrm() {
        String randomNum = RandomStringUtil.getRandomNumber(8);
        String token = null;
        try {
            token = DesUtil.encrypt(randomNum + enjoyCrmConfig.getUserno(), enjoyCrmConfig.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = enjoyCrmConfig.getService_url();
        GetTokenRequest req = new GetTokenRequest();
        req.setUniqueKey("获取令牌");
        req.setClientTime(LocalDateTime.now().toString());
        req.setUserNo(enjoyCrmConfig.getUserno());
        req.setToken(token);

        try {
            String params = JsonUtil.toJsonString(req);
            String respContent = HttpUtils.httpPostRequest(url, params);
            GetTokenResponse resp = JsonUtil.jsonToObject(respContent, GetTokenResponse.class);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*@Override
    public void initEnjoyConfig(EnjoyCrmConfig enjoyCrmConfig) {
        this.enjoyCrmConfig = enjoyCrmConfig;
    }*/
}
