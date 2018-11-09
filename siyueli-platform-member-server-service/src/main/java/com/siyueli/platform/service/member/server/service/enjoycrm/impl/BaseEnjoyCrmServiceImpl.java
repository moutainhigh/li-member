package com.siyueli.platform.service.member.server.service.enjoycrm.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmRequest;
import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.EnjoyCrmException;
import com.siyueli.platform.member.vo.enjoycrm.gettoken.GetTokenResponse;
import com.siyueli.platform.service.member.server.config.EnjoyCrmConfig;
import com.siyueli.platform.service.member.server.service.enjoycrm.BaseEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.TokenEnjoyCrmService;
import com.siyueli.platform.service.member.server.util.DesUtil;
import com.siyueli.platform.service.member.server.util.HttpUtils;
import com.siyueli.platform.service.member.server.util.JsonUtil;
import com.siyueli.platform.service.member.server.util.MD5Maker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseEnjoyCrmServiceImpl implements BaseEnjoyCrmService {

    @Autowired
    private EnjoyCrmConfig enjoyCrmConfig;

    @Autowired
    private TokenEnjoyCrmService tokenEnjoyCrmService;



    private String invokeService(String uniqueKey, Object data, String token) throws Exception {
        String url = enjoyCrmConfig.getService_url();
        BaseEnjoyCrmRequest req = new BaseEnjoyCrmRequest();
        req.setUniqueKey(uniqueKey);
        req.setClientTime(LocalDateTime.now().toString());
        req.setUserNo(enjoyCrmConfig.getUserno());

        req.setObjectData(data);
        String reqStr = JsonUtil.toJsonString(req);
        String randomNum = DesUtil.decrypt(token, enjoyCrmConfig.getKey());
        String sessionKey = MD5Maker.getMD5(randomNum + reqStr);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Session-Key", sessionKey);
        String respContent = HttpUtils.httpPostRequest(url, reqStr, headers);
        return respContent;
    }

    private BaseEnjoyCrmResponse parseJson(JsonNode jsonNode) throws IOException {
        String UniqueKey = jsonNode.path("UniqueKey").textValue();
        String MethodName = jsonNode.path("MethodName").textValue();
        String Tag = jsonNode.path("Tag").textValue();
        String UserState = jsonNode.path("UserState").textValue();
        JsonNode ExceptionNode = jsonNode.path("Exception");
        EnjoyCrmException Exception = null;
        if (!JsonUtil.isNullNode(ExceptionNode))
            Exception = JsonUtil.jsonToObject(ExceptionNode, EnjoyCrmException.class);

        JsonNode hasExceptionNode = jsonNode.path("HasException");
        Boolean hasException = null;
        if (!JsonUtil.isNullNode(hasExceptionNode)) {
            hasException = hasExceptionNode.booleanValue();
        }


        BaseEnjoyCrmResponse resp = new BaseEnjoyCrmResponse();
        resp.setUniqueKey(UniqueKey);
        resp.setMethodName(MethodName);
        resp.setTag(Tag);
        resp.setUserState(UserState);
        resp.setException(Exception);
        resp.setHasException(hasException);

        return resp;
    }

    private <T> T getRespData(JsonNode ObjectData, Class<T> clazz) throws IOException {
        T respData = null;
        if (!JsonUtil.isNullNode(ObjectData))
            respData = JsonUtil.jsonToObject(ObjectData, clazz);
        return respData;

    }

    private <T> List<T> getRespDataList(JsonNode ObjectData, Class<T> clazz) throws IOException {
        List<T> respData = null;
        if (!JsonUtil.isNullNode(ObjectData))
            respData = JsonUtil.jsonToObject(ObjectData, List.class, clazz);
        return respData;

    }

    private Map getResponseBaseInfo(String uniqueKey, Object data) throws Exception {
        GetTokenResponse tokenResp = tokenEnjoyCrmService.getToken(false);
        String respContent = invokeService(uniqueKey, data, tokenResp.getObjectData());
        JsonNode jsonNode= JsonUtil.parseJson(respContent);
        BaseEnjoyCrmResponse resp = parseJson(jsonNode);

        // token过期
        if (resp != null && resp.getHasException() && resp.getException() != null && "004".equals(resp.getException().getCode())) {
            tokenResp = tokenEnjoyCrmService.getToken(true);
            respContent = invokeService(uniqueKey, data, tokenResp.getObjectData());
            jsonNode= JsonUtil.parseJson(respContent);
            resp = parseJson(jsonNode);
        }

        Map map = new HashMap();
        map.put("resp", resp);
        map.put("jsonNode", jsonNode);
        return map;

    }

    @Override
    public <T> BaseEnjoyCrmResponse<T> invokeEnjoyCrmService(String uniqueKey, Object data, Class<T> clazz) {
        try {
            Map map = getResponseBaseInfo(uniqueKey, data);
            JsonNode jsonNode = (JsonNode)map.get("jsonNode");
            BaseEnjoyCrmResponse<T> resp = (BaseEnjoyCrmResponse)map.get("resp");

            JsonNode ObjectData = jsonNode.path("ObjectData");
            T respData = getRespData(ObjectData, clazz);
            resp.setObjectData(respData);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> BaseEnjoyCrmResponse<List<T>> invokeEnjoyCrmServiceList(String uniqueKey, Object data, Class<T> clazz) {
        try {
            Map map = getResponseBaseInfo(uniqueKey, data);
            JsonNode jsonNode = (JsonNode)map.get("jsonNode");
            BaseEnjoyCrmResponse<List<T>> resp = (BaseEnjoyCrmResponse)map.get("resp");

            JsonNode ObjectData = jsonNode.path("ObjectData");
            List<T> respData = getRespDataList(ObjectData, clazz);
            resp.setObjectData(respData);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
