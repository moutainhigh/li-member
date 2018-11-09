package com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.updatememberinfo;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class UpdateMemberInfoReqData extends BaseObjectData {
    private String strCustomerNo;

    private String strCardNo;

    private String strName;

    private String strSex;

    private String dtBirthDay;

    private String strIdType;

    private String strID;

    private String strAddress;

    private String strPostCode;

    private String strTelePhone;

    private String strMobile;

    private String strGroup;

    private String strJob;

    private String strCorp;

    private String strEducation;

    private String strPUserno;

    private String strEmail;

    private String strFond;
}
