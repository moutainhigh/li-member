package com.siyueli.platform.service.member.server.service.enjoycrm.impl;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.memberpoint.addpoint.AddPointReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberpoint.addpoint.AddPointRespData;
import com.siyueli.platform.service.member.server.service.enjoycrm.BaseEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberPointEnjoyCrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员积分服务
 */
@Service
public class MemberPointEnjoyCrmServiceImpl implements MemberPointEnjoyCrmService {

    @Autowired
    private BaseEnjoyCrmService baseEnjoyCrmService;

    /**
     * 2.6.2.1 增减积分
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<AddPointRespData> addPoint(AddPointReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmService("增减积分", data, AddPointRespData.class);
    }
}
