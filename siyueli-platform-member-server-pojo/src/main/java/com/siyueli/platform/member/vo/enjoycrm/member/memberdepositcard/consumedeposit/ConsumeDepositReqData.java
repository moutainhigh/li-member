package com.siyueli.platform.member.vo.enjoycrm.member.memberdepositcard.consumedeposit;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class ConsumeDepositReqData extends BaseObjectData {

    private String strGuid;

    private String strSourceGuid;

    private String strCardNo;

    private Integer decSale;

    private Integer decPay;

    private Integer decDeposit;

    private String strVerify;

    private String strUserName;

    private String strId;

    private String strNote;

    private String strStoreId;

    private String strDepositType;

    private String strType;
}
