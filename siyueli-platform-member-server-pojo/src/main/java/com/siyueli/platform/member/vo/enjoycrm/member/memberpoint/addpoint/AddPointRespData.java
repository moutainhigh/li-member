package com.siyueli.platform.member.vo.enjoycrm.member.memberpoint.addpoint;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddPointRespData {
    private String strCardNo;

    private BigDecimal decScore;

    private BigDecimal decUsedScore;

    private BigDecimal decCutScore;

    private BigDecimal decCanUseScore;
}
