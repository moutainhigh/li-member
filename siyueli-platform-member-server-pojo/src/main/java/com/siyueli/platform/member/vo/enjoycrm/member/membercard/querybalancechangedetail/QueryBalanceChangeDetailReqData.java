package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querybalancechangedetail;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class QueryBalanceChangeDetailReqData extends BaseObjectData {

    private String strCustomerNo;

    private String dtStartDate ;

    private String dtEndDate;

    private Integer iStartRow;

    private Integer iEndRow;
}
