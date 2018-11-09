package com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.unbindmember;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class UnbindMemberReqData extends BaseObjectData {
    private String strCardNo;

    private String strPassPort;
}
