package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querybalancechangedetail;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LstDeposits {
    private String strStoreId;

    private String strCardNo;

    private String strType;

    private String strID;

    private BigDecimal decAmount;

    private String dtDate;

    private String strUserno;

    private String strNote;

    private String strCustName;

    private String strCustMobile;

    private String strStoreName;
}
