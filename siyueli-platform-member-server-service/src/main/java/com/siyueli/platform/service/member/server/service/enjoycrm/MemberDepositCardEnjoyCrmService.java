package com.siyueli.platform.service.member.server.service.enjoycrm;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.memberdepositcard.consumedeposit.ConsumeDepositReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberdepositcard.putorgetdeposit.PutOrGetDepositReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberdepositcard.putorgetdeposit.PutOrGetDepositRespData;

public interface MemberDepositCardEnjoyCrmService {

    public BaseEnjoyCrmResponse<PutOrGetDepositRespData> putOrGetDeposit(PutOrGetDepositReqData data);

    public BaseEnjoyCrmResponse<Boolean> consumeDeposit(ConsumeDepositReqData data);

    public BaseEnjoyCrmResponse<String> queryPutOrGetDepositResult(String data);
}
