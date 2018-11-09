package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardinfo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryCardInfoRespData {
    private String strCardNo;

    private String strGrade;

    private String strCardName;

    private BigDecimal decComsumed;

    private BigDecimal decPaid;

    private BigDecimal decDeposit;

    private String strType;

    private String strDiscType;

    private String strStatus;

    private String dtStartDate;

    private String dtEndDate;

    private String strCustomerNo;

    private String strCardMid;
}
