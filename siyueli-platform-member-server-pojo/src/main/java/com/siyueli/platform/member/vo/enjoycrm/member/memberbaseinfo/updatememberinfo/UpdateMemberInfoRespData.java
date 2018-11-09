package com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.updatememberinfo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateMemberInfoRespData {
    private String strCardNo;

    private String strNo;

    private String strName;

    private String strSex;

    private String strID;

    private String strMobile;

    private BigDecimal decScore;

    private BigDecimal decCanUseScore;
}
