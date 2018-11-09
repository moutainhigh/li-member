package com.siyueli.platform.member.vo.enjoycrm.member.memberpoint.addpoint;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class AddPointReqData extends BaseObjectData {
    private String strGuid;

    private String strCustNo;

    private String strCardNo;

    private String decScore;

    private String strSaleId;

    private String decSaleAmount;

    private String strType;

    private String strStoreId;

    private String strUserno;

    private String strNote;

    private String strAccountName;
}
