package com.siyueli.platform.service.member.server.service.enjoycrm.impl;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.memberdepositcard.consumedeposit.ConsumeDepositReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberdepositcard.putorgetdeposit.PutOrGetDepositReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberdepositcard.putorgetdeposit.PutOrGetDepositRespData;
import com.siyueli.platform.service.member.server.service.enjoycrm.BaseEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberDepositCardEnjoyCrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 储值卡服务
 */
@Service
public class MemberDepositCardEnjoyCrmServiceImpl implements MemberDepositCardEnjoyCrmService {

    @Autowired
    private BaseEnjoyCrmService baseEnjoyCrmService;


    /**
     * 2.6.3.1 卡存取款
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<PutOrGetDepositRespData> putOrGetDeposit(PutOrGetDepositReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmService("卡存取款", data, PutOrGetDepositRespData.class);
    }

    /**
     * 2.6.3.5 卡消费
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<Boolean> consumeDeposit(ConsumeDepositReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmService("卡消费", data, Boolean.class);
    }

    /**
     * 2.6.3.10 卡存取款查询
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<String> queryPutOrGetDepositResult(String data) {
        return baseEnjoyCrmService.invokeEnjoyCrmService("卡存取款查询", data, String.class);
    }
}
