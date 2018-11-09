package com.siyueli.platform.service.member.server.service.enjoycrm;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.addorupdatememberextinfo.AddOrUpdateMemberExtInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.addorupdatememberextinfo.AddOrUpdateMemberExtInfoRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.bindmember.BindMemberReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.bindmember.BindMemberRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.querymemberinfo.QueryMemberInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.querymemberinfo.QueryMemberInfoRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.registermember.RegisterMemberReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.registermember.RegisterMemberRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.unbindmember.UnbindMemberReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.unbindmember.UnbindMemberRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.updatememberinfo.UpdateMemberInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.updatememberinfo.UpdateMemberInfoRespData;

public interface MemberInfoEnjoyCrmService {

    BaseEnjoyCrmResponse<QueryMemberInfoRespData> queryMemberInfo(QueryMemberInfoReqData data);

    BaseEnjoyCrmResponse<RegisterMemberRespData> registerMember(RegisterMemberReqData data);

    BaseEnjoyCrmResponse<UpdateMemberInfoRespData> updateMemberInfo(UpdateMemberInfoReqData data);

    BaseEnjoyCrmResponse<BindMemberRespData> bindMember(BindMemberReqData data);

    BaseEnjoyCrmResponse<UnbindMemberRespData> unbindMember(UnbindMemberReqData data);

    BaseEnjoyCrmResponse<AddOrUpdateMemberExtInfoRespData> addOrUpdateMemberExtInfo(AddOrUpdateMemberExtInfoReqData data);


}
