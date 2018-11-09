package com.siyueli.platform.service.member.server.service.enjoycrm;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;

import java.util.List;

public interface BaseEnjoyCrmService {

    <T> BaseEnjoyCrmResponse<T> invokeEnjoyCrmService(String uniqueKey, Object data, Class<T> clazz);

    public <T> BaseEnjoyCrmResponse<List<T>> invokeEnjoyCrmServiceList(String uniqueKey, Object data, Class<T> clazz);
}
