package com.siyueli.platform.member.vo.enjoycrm.member.memberdepositcard.putorgetdeposit;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class PutOrGetDepositReqData extends BaseObjectData {
    private String strCardNo;

    private String decAmount;

    private String decDeposit;

    private String strUserNo;

    private String strFlowNo;

    private String strNote;

    private String strStoreId;

    private String strDepositType;

    private String strGuid;
}
