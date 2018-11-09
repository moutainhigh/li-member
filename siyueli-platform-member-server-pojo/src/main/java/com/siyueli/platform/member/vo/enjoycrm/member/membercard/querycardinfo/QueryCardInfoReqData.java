package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardinfo;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class QueryCardInfoReqData extends BaseObjectData {

    private String strCardNo;

    private String strCustomerNo;

    private String strIsDeposit;

    private String strType;

    private String strAllCard;
}
