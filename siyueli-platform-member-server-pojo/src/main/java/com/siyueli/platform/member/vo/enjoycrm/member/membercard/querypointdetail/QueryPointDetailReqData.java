package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querypointdetail;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class QueryPointDetailReqData extends BaseObjectData {
    private String strCardNo;

    private String strCustomerNo;

    private String strAccountName;

    private String dtStartDate;

    private String dtEndDate;

    private String iStartRow;

    private String iEndRow;

    private String strId ;
}
