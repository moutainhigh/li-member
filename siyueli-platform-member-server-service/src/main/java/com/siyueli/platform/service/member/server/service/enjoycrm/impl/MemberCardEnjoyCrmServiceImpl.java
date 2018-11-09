package com.siyueli.platform.service.member.server.service.enjoycrm.impl;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.publishcard.PublishCardReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.publishcard.PublishCardRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querybalancechangedetail.QueryBalanceChangeDetailReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querybalancechangedetail.QueryBalanceChangeDetailRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardaccountinfo.QueryCardAccountInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardaccountinfo.QueryCardAccountInfoRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardconsumedetail.QueryCardConsumeDetailReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardconsumedetail.QueryCardConsumeDetailRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardinfo.QueryCardInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardinfo.QueryCardInfoRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.queryconsumedetail.QueryConsumeDetailReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.queryconsumedetail.QueryConsumeDetailRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querypointdetail.QueryPointDetailReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querypointdetail.QueryPointDetailRespData;
import com.siyueli.platform.service.member.server.service.enjoycrm.BaseEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberCardEnjoyCrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员卡服务
 */
@Service
public class MemberCardEnjoyCrmServiceImpl implements MemberCardEnjoyCrmService {

    @Autowired
    private BaseEnjoyCrmService baseEnjoyCrmService;

    /**
     * 2.6.2.3 查询卡明细
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<List<QueryCardInfoRespData>> queryCardInfo(QueryCardInfoReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmServiceList("查询卡明细", data, QueryCardInfoRespData.class);
    }

    /**
     * 2.6.2.4 查询卡账户信息
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<List<QueryCardAccountInfoRespData>> queryCardAccountInfo(QueryCardAccountInfoReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmServiceList("查询卡账户信息", data, QueryCardAccountInfoRespData.class);
    }

    /**
     * 2.6.2.5 查询余额变动明细
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<QueryBalanceChangeDetailRespData> queryBalanceChangeDetail(QueryBalanceChangeDetailReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmService("查询余额变动明细", data, QueryBalanceChangeDetailRespData.class);
    }

    /**
     * 2.6.2.6 查询卡消费明细信息
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<List<QueryCardConsumeDetailRespData>> queryCardConsumeDetail(QueryCardConsumeDetailReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmServiceList("查询卡消费明细信息", data, QueryCardConsumeDetailRespData.class);
    }

    /**
     * 2.6.2.7 查询积分明细
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<QueryPointDetailRespData> queryPointDetail(QueryPointDetailReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmService("查询积分明细", data, QueryPointDetailRespData.class);
    }

    /**
     * 2.6.2.8 查询消费明细信息
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<List<QueryConsumeDetailRespData>> queryConsumeDetail(QueryConsumeDetailReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmServiceList("查询消费明细信息", data, QueryConsumeDetailRespData.class);
    }

    /**
     * 2.6.2.10 卡发行接口
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<PublishCardRespData> publishCard(PublishCardReqData data) {
        return baseEnjoyCrmService.invokeEnjoyCrmService("卡发行接口", data, PublishCardRespData.class);
    }
}
