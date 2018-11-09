package com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.addorupdatememberextinfo;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class AddOrUpdateMemberExtInfoReqData extends BaseObjectData {
    private String strCustNo;

    private String strCardNo;

    private String strGuid;

    private String strCustExType;

    private String strCode;

    private String strCode2;

    private String strNote;

    private String strUserno;
}
