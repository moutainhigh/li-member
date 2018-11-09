package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardconsumedetail;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryCardConsumeDetailRespData {

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
