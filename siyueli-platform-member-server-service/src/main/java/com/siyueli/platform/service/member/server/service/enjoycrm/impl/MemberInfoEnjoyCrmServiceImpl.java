package com.siyueli.platform.service.member.server.service.enjoycrm.impl;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
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
import com.siyueli.platform.service.member.server.service.enjoycrm.BaseEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberInfoEnjoyCrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员基本信息服务
 */
@Service
public class MemberInfoEnjoyCrmServiceImpl implements MemberInfoEnjoyCrmService {

    @Autowired
    private BaseEnjoyCrmService baseEnjoyCrmService;

    private <T> BaseEnjoyCrmResponse<T> invokeEnjoyCrmService(String uniqueKey, BaseObjectData data, Class<T> clazz) {
        return baseEnjoyCrmService.invokeEnjoyCrmService(uniqueKey, data, clazz);
    }


    /**
     * 2.6.1.1 查询会员信息
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<QueryMemberInfoRespData> queryMemberInfo(QueryMemberInfoReqData data) {
        return invokeEnjoyCrmService("查询会员信息", data, QueryMemberInfoRespData.class);
    }

    /**
     * 2.6.1.2 会员注册
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<RegisterMemberRespData> registerMember(RegisterMemberReqData data) {
        return invokeEnjoyCrmService("会员注册", data, RegisterMemberRespData.class);
    }

    /**
     * 2.6.1.3 会员档案修改
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<UpdateMemberInfoRespData> updateMemberInfo(UpdateMemberInfoReqData data) {
        return invokeEnjoyCrmService("会员档案修改", data, UpdateMemberInfoRespData.class);
    }

    /**
     * 2.6.1.4 会员绑定
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<BindMemberRespData> bindMember(BindMemberReqData data) {
        return invokeEnjoyCrmService("会员绑定", data, BindMemberRespData.class);
    }

    /**
     * 2.6.1.5 会员解绑
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<UnbindMemberRespData> unbindMember(UnbindMemberReqData data) {
        return invokeEnjoyCrmService("会员解绑", data, UnbindMemberRespData.class);
    }

    /**
     * 2.6.1.7 新增修改顾客扩展信息
     * @param data
     * @return
     */
    @Override
    public BaseEnjoyCrmResponse<AddOrUpdateMemberExtInfoRespData> addOrUpdateMemberExtInfo(AddOrUpdateMemberExtInfoReqData data) {
        return invokeEnjoyCrmService("新增修改顾客扩展信息", data, AddOrUpdateMemberExtInfoRespData.class);
    }
}
