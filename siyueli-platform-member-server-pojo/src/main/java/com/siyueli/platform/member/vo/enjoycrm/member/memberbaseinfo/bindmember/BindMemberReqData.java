package com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.bindmember;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class BindMemberReqData extends BaseObjectData {
    private String strCardNo;

    private String strName;

    private String strTel;

    private String strId;

    private String strPassPort;

    private String strAppid;
}
