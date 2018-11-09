package com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.bindmember;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BindMemberRespData {
    private String strCardNo;

    private String strCustNo;

    private String sName;

    private String sMob;

    private String sTel;

    private String sId;

    private String sSex;

    private String sBir;

    private String sAdress;

    private String sPassPort;

    private String sPassPortDt;

    private BigDecimal decCanUseScore;
}
