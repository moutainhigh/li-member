package com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.querymemberinfo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LstScoreAccount {

    private String strNo;
    private String strAccountName;
    private BigDecimal dScore;
    private BigDecimal dUsedScore;
    private BigDecimal dCutScore;
    private String strFirstDt;
    private String strLastDt;
    private BigDecimal dLastScore;
    private BigDecimal dLastUsedScore;
    private BigDecimal dLastCutScore;
    private String strLastScoreDt;
}
