package com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.querymemberinfo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class QueryMemberInfoRespData {
    private String dtEndDt;

    private String strNo;

    private String strName;

    private String strSex;

    private String strIDType;

    private String strID;

    private String strMobile;

    private BigDecimal decScore;

    private BigDecimal decCanUseScore;

    private String dtBirthDay;

    private String strAddress;

    private String strPostCode;

    private String strTelephone;

    private BigDecimal decUsedScore;

    private Integer iCardCount;

    private BigDecimal decCardAmount;

    private String strCards;

    private Integer iCardDepositCount;

    private BigDecimal decCardDepositAmount;

    private String strCardDespoists;

    private String strGroup;

    private String strJob;

    private String strCorp;

    private String strEducation;

    private String strCardMedia;

    private String strCard;

    private String strCardStatus;

    private String strMid;

    private String strMicroId;

    private String strCardName;

    private String strCardEndDt;

    private BigDecimal decEndScore;

    private BigDecimal decEndAmount;

    private List<LstRel> lstRel;

    private List<LstScoreAccount> lstScoreAccount;

    private List<LstEx> lstEx;

    private String strCardDiscType;

    private String strEmail;

    private String strFond;

    private String strCardVerifyMethod;

    private String strCardVerifyInfo;

    private Integer iCustomerCount;

    private BigDecimal decMainCardAmount;
}
