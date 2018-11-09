package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardconsumedetail;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class QueryCardConsumeDetailReqData extends BaseObjectData {
    private String strCustomerNo;

    private String strCtype;

    private String dtStartDate;

    private String dtEndDate;

    private Integer iStartRow;

    private Integer iEndRow;
}
