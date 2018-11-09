package com.siyueli.platform.member.vo.enjoycrm.member.membercard.queryconsumedetail;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryConsumeDetailRespData {

    private String strStoreId;
    private String strComputerId;
    private String strCashier;
    private String strID;
    private String dtSaleDate;
    private String strCardNo;
    private String strName;
    private BigDecimal decQtty;
    private BigDecimal decAmount;
    private BigDecimal decScore;
}
