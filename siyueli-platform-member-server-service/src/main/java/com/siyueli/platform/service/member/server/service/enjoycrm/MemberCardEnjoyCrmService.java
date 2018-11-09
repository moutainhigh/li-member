package com.siyueli.platform.service.member.server.service.enjoycrm;

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

import java.util.List;

public interface MemberCardEnjoyCrmService {

    public BaseEnjoyCrmResponse<List<QueryCardInfoRespData>> queryCardInfo(QueryCardInfoReqData data);

    public BaseEnjoyCrmResponse<List<QueryCardAccountInfoRespData>> queryCardAccountInfo(QueryCardAccountInfoReqData data);

    public BaseEnjoyCrmResponse<QueryBalanceChangeDetailRespData> queryBalanceChangeDetail(QueryBalanceChangeDetailReqData data);

    public BaseEnjoyCrmResponse<List<QueryCardConsumeDetailRespData>> queryCardConsumeDetail(QueryCardConsumeDetailReqData data);

    public BaseEnjoyCrmResponse<QueryPointDetailRespData> queryPointDetail(QueryPointDetailReqData data);

    public BaseEnjoyCrmResponse<List<QueryConsumeDetailRespData>> queryConsumeDetail(QueryConsumeDetailReqData data);

    public BaseEnjoyCrmResponse<PublishCardRespData> publishCard(PublishCardReqData data);


}
