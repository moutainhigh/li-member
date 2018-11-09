package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardaccountinfo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryCardAccountInfoRespData {
    private String strCardNo;

    private String strDepositType;

    private BigDecimal decDeposit;

    private BigDecimal c_save;

    private BigDecimal c_paid;

    private String strStatus;

    private String dtStartDate;

    private String dtEndDate;
}
