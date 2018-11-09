package com.siyueli.platform.member.vo.enjoycrm.member.membercard.queryconsumedetail;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class QueryConsumeDetailReqData extends BaseObjectData {
    private String strCustomerNo;

    private String dtStartDate;

    private String dtEndDate;

    private String iStartRow;

    private String iEndRow;
}
