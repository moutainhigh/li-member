package com.siyueli.platform.service.member.server.service.enjoycrm;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.memberpoint.addpoint.AddPointReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberpoint.addpoint.AddPointRespData;

public interface MemberPointEnjoyCrmService {

    public BaseEnjoyCrmResponse<AddPointRespData> addPoint(AddPointReqData data);
}
